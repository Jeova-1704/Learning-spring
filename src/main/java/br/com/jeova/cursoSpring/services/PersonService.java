package br.com.jeova.cursoSpring.services;

import br.com.jeova.cursoSpring.controllers.PersonController;
import br.com.jeova.cursoSpring.DTO.PersonDTO;
import br.com.jeova.cursoSpring.exceptions.RequiredObjectIsNullException;
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

    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<PersonDTO> findALl() {
        logger.info("Finding all people");
        List<PersonDTO> persons =  DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonDTO vo = DozerMapper.parseObject(entity, PersonDTO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonDTO create(PersonDTO personVO) {

        if (personVO == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one person");
        Person person = DozerMapper.parseObject(personVO, Person.class);
        PersonDTO vo = DozerMapper.parseObject(repository.save(person), PersonDTO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonDTO update(PersonDTO personVO) {

        if (personVO == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one person");
        Person person = repository.findById(personVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No Records found for this Id!"));

        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setAddress(personVO.getAddress());
        person.setGender(personVO.getGender());

        PersonDTO vo = DozerMapper.parseObject(repository.save(person), PersonDTO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Records found for this Id!"));
        repository.delete(person);
    }
}
