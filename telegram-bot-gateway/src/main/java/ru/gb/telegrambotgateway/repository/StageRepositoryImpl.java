package ru.gb.telegrambotgateway.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import ru.gb.telegrambotgateway.model.Stage;

@RequiredArgsConstructor
@Repository
public class StageRepositoryImpl implements StageRepository {
    private static final String KEY = "Stage";
    private final HashOperations stageHashOperations;

    public Stage getByChatId(Long chatId) {
        return (Stage) stageHashOperations.get(KEY, chatId);
    }

    public void save(Long chatId, Stage stage) {
        stageHashOperations.put(KEY, chatId, stage);
    }


}
