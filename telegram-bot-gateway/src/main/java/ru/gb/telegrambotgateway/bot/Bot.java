package ru.gb.telegrambotgateway.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambotgateway.button.Button;
import ru.gb.telegrambotgateway.factory.ButtonFactory;
import ru.gb.telegrambotgateway.factory.HandlerFactory;
import ru.gb.telegrambotgateway.handler.Handler;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.StageService;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final HandlerFactory handlerFactory;
    private final ButtonFactory buttonFactory;
    private final StageService stageService;

    @Value("${bot.name}")
    String botUsername;

    @Value("${bot.token}")
    String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        Stage stage = stageService.getByChatId(chatId);
        Handler handler = handlerFactory.getHandler(stage);
        ResponseMessage responseMessage = handler.handle(chatId, text);
        stageService.save(chatId, responseMessage.getButtonStage());

        Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
        button.setButton(responseMessage.getSendMessage());

        try {
            execute(responseMessage.getSendMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
