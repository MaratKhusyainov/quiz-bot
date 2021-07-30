package ru.gb.questionapi.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.questionapi.dao.QuestionRepository;
import ru.gb.questionapi.domain.Question;



@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveOrUpdate(Question question) {
        questionRepository.save(question);
    }


}
