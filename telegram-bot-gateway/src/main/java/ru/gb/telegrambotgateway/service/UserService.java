package ru.gb.telegrambotgateway.service;

import ru.gb.telegrambotgateway.model.Stage;


public interface UserService {

    Stage getByChatId(Long chatId);

    void save(Long chatId, Stage stage);

}
