package br.com.jeova.cursoSpring.services;

import br.com.jeova.cursoSpring.controllers.PersonController;
import br.com.jeova.cursoSpring.data.vo.v1.PersonVO;
import br.com.jeova.cursoSpring.exceptions.ResourceNotFoundException;
import br.com.jeova.cursoSpring.mapper.DozerMapper;
import br.com.jeova.cursoSpring.model.Person;
import br.com.jeova.cursoSpring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<PersonVO> findALl() {
        logger.info("Finding all people");
        List<PersonVO> persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person");
        Person person = DozerMapper.parseObject(personVO, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one person");
        Person person = repository.findById(personVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No Records found for this Id!"));

        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setAddress(personVO.getAddress());
        person.setGender(personVO.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void Delete(Long id) {
        logger.info("Deleting one person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Records found for this Id!"));
        repository.delete(person);
    }
}
