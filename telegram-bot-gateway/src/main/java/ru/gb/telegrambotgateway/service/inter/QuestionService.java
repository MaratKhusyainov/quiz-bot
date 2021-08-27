package ru.gb.telegrambotgateway.service.inter;

import ru.gb.telegrambotgateway.model.QuestionDto;

public interface QuestionService {
    QuestionDto getQuestion(Long chatId);

    void answer(Long chatId, QuestionDto question, boolean isCorrect);

    QuestionDto getByChatId(Long chatId);

    void save(Long chatId, QuestionDto questionDto);

    void delete(Long chatId);
}
