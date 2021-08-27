package ru.gb.telegrambotgateway.service.inter;

import ru.gb.telegrambotgateway.model.Stage;


public interface StageService {

    Stage getByChatId(Long chatId);

    void save(Long chatId, Stage stage);

}
