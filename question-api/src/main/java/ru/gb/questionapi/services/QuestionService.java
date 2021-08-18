package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.QuestionRepository;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.exceptions.QuestionNotFoundException;

import javax.transaction.Transactional;


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

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Can't found question with id = " + id));
    }

    @Transactional
    public Question findNewQuestion(Long userId){
        return questionRepository.findOneNewQuestion(userId);

    }

}
