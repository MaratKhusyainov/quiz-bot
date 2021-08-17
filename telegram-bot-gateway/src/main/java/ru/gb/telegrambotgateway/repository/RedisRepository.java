package ru.gb.telegrambotgateway.repository;


import ru.gb.telegrambotgateway.model.Stage;

public interface RedisRepository {

    Stage getByChatId(Long chatId);

    void save(Long chatId, Stage stage);
}
