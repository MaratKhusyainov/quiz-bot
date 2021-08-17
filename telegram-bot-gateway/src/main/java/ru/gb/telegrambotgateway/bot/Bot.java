package ru.gb.telegrambotgateway.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambotgateway.button.Button;
import ru.gb.telegrambotgateway.factory.ButtonFactory;
import ru.gb.telegrambotgateway.factory.HandlerFactory;
import ru.gb.telegrambotgateway.handler.Handler;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final HandlerFactory handlerFactory;
    private final ButtonFactory buttonFactory;

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
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        Stage stage = Stage.MAIN; //TODO mock
        Handler handler = handlerFactory.getHandler(stage);
        ResponseMessage responseMessage = handler.handle(chatId, text);

        Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
        button.setButton(responseMessage.getSendMessage());

        try {
            execute(responseMessage.getSendMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
