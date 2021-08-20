package ru.gb.telegrambotgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.telegrambotgateway.model.Question;
import ru.gb.telegrambotgateway.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question getQuestion(Long chatId) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, "a", "a1", "a2", "a3", "a4"));
        questions.add(new Question(2L, "b", "b1", "b2", "b3", "b4"));
        questions.add(new Question(3L, "c", "c1", "c2", "c3", "c4"));
        questions.add(new Question(3L, "d", "d1", "d2", "d3", "d4"));

        Question question = getByChatId(chatId);
        if (question == null) {
            question = questions.get((int) (Math.random() * 4));
        }

        save(chatId, question);
        return question;
    }

    @Override
    public void answer(Long chatId) {
        delete(chatId);
    }

    @Override
    public Question getByChatId(Long chatId) {
        return questionRepository.getByChatId(chatId);
    }

    @Override
    public void save(Long chatId, Question question) {
        questionRepository.save(chatId, question);
    }

    @Override
    public void delete(Long chatId) {
        questionRepository.delete(chatId);
    }

}
