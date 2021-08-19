package ru.gb.telegrambotgateway.service;

import ru.gb.telegrambotgateway.model.Question;

public interface QuestionService {
    Question getQuestion(Long chatId);

    void answer(Long chatId);

    Question getByChatId(Long chatId);

    void save(Long chatId, Question question);

    void delete(Long chatId);
}
