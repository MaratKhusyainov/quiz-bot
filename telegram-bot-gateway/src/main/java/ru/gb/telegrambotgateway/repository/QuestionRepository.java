package ru.gb.telegrambotgateway.repository;

import ru.gb.telegrambotgateway.model.Question;

public interface QuestionRepository {

    Question getByChatId(Long chatId);

    void save(Long chatId, Question question);

    void delete(Long chatId);
}
