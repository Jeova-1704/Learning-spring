package br.com.jeova.cursoSpring.repository;

import br.com.jeova.cursoSpring.model.Book;
import br.com.jeova.cursoSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
