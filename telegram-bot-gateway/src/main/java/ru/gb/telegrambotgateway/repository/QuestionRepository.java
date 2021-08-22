package ru.gb.telegrambotgateway.repository;

import ru.gb.telegrambotgateway.model.QuestionDto;

public interface QuestionRepository {

    QuestionDto getByChatId(Long chatId);

    void save(Long chatId, QuestionDto questionDto);

    void delete(Long chatId);
}
