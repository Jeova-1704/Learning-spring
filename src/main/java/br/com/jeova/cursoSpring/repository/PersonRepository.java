package br.com.jeova.cursoSpring.repository;

import br.com.jeova.cursoSpring.data.vo.v1.PersonVO;
import br.com.jeova.cursoSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
