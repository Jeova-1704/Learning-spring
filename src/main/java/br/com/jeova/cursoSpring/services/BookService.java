package br.com.jeova.cursoSpring.services;

import br.com.jeova.cursoSpring.DTO.BookDTO;
import br.com.jeova.cursoSpring.controllers.BookController;
import br.com.jeova.cursoSpring.exceptions.RequiredObjectIsNullException;
import br.com.jeova.cursoSpring.exceptions.ResourceNotFoundException;
import br.com.jeova.cursoSpring.mapper.DozerMapper;
import br.com.jeova.cursoSpring.model.Book;
import br.com.jeova.cursoSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    @Autowired
    private BookRepository Repository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<BookDTO> findAll (){
        logger.info("finding all books");
        List<BookDTO> books = DozerMapper.parseListObjects(Repository.findAll(), BookDTO.class);
        books
                .stream()
                .forEach(book -> book.add(linkTo(methodOn(BookController.class).findByid(book.getKey())).withSelfRel()));
        return books;
    }

    public BookDTO findById(Integer id){
        logger.info("finding one book");
        Book book = Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookDTO entityDTO = DozerMapper.parseObject(book, BookDTO.class);
        entityDTO.add(linkTo(methodOn(BookController.class).findByid(id)).withSelfRel());
        return entityDTO;
    }

    public BookDTO create(BookDTO bookDTO) {

        if(bookDTO == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one person");
        Book book = DozerMapper.parseObject(bookDTO, Book.class);
        BookDTO entityDTO = DozerMapper.parseObject(Repository.save(book), BookDTO.class);
        entityDTO.add(linkTo(methodOn(BookController.class).findByid(entityDTO.getKey())).withSelfRel());
        return entityDTO;
    }

    public BookDTO update(BookDTO bookDTO) {

        if (bookDTO == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one person");

        Book book = Repository.findById(bookDTO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No Records found for this Id!"));

        book.setAuthor(bookDTO.getAuthor());
        book.setLaunch_date(bookDTO.getLaunch_date());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());

        BookDTO entityDTO = DozerMapper.parseObject(Repository.save(book), BookDTO.class);
        entityDTO.add(linkTo(methodOn(BookController.class).findByid(entityDTO.getKey())).withSelfRel());
        return entityDTO;
    }

    public void delete(Integer id){
        logger.info("Deleting one book");
        Book book = Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Records foun for this Id!"));
        Repository.delete(book);
    }
}
