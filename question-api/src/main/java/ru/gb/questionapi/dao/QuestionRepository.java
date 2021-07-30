package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.questionapi.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
