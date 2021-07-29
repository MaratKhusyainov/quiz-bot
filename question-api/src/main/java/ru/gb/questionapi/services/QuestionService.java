package ru.gb.questionapi.services;

import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.QuestionRepository;
import ru.gb.questionapi.domain.Question;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public void saveOrUpdate(Question question) {
        questionRepository.save(question);
    }


}
