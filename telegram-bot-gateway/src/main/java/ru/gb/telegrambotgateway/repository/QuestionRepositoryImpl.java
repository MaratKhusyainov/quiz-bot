package ru.gb.telegrambotgateway.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import ru.gb.telegrambotgateway.model.QuestionDto;

@RequiredArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private static final String KEY = "Question";
    private final HashOperations<String, Long, QuestionDto> hashOperations;

    @Override
    public QuestionDto getByChatId(Long chatId) {
        return (QuestionDto) hashOperations.get(KEY, chatId);
    }

    @Override
    public void save(Long chatId, QuestionDto questionDto) {
        hashOperations.put(KEY, chatId, questionDto);
    }

    @Override
    public void delete(Long chatId) {
        hashOperations.delete(KEY, chatId);
    }

}
