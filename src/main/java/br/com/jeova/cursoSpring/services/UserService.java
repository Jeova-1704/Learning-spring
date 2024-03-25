package br.com.jeova.cursoSpring.services;

import br.com.jeova.cursoSpring.DTO.PersonDTO;
import br.com.jeova.cursoSpring.controllers.PersonController;
import br.com.jeova.cursoSpring.exceptions.RequiredObjectIsNullException;
import br.com.jeova.cursoSpring.exceptions.ResourceNotFoundException;
import br.com.jeova.cursoSpring.mapper.DozerMapper;
import br.com.jeova.cursoSpring.model.Person;
import br.com.jeova.cursoSpring.model.User;
import br.com.jeova.cursoSpring.repository.PersonRepository;
import br.com.jeova.cursoSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name " + username+ "!");
        User user = repository.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            throw  new UsernameNotFoundException("Username " + username + "not found!");
        }
    }
}
