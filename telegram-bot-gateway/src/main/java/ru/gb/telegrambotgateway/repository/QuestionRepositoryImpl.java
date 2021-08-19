package ru.gb.telegrambotgateway.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import ru.gb.telegrambotgateway.model.Question;

@RequiredArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private static final String KEY = "Question";
    private final HashOperations<String, Long, Question> hashOperations;

    @Override
    public Question getByChatId(Long chatId) {
        return (Question) hashOperations.get(KEY, chatId);
    }

    @Override
    public void save(Long chatId, Question question) {
        hashOperations.put(KEY, chatId, question);
    }

    @Override
    public void delete(Long chatId) {
        hashOperations.delete(KEY, chatId);
    }

}
