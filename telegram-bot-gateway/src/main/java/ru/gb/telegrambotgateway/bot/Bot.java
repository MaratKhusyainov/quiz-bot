package ru.gb.telegrambotgateway.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambotgateway.button.Button;
import ru.gb.telegrambotgateway.factory.ButtonFactory;
import ru.gb.telegrambotgateway.factory.HandlerFactory;
import ru.gb.telegrambotgateway.handler.Handler;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.model.ThreadState;
import ru.gb.telegrambotgateway.service.StageService;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final HandlerFactory handlerFactory;
    private final ButtonFactory buttonFactory;
    private final StageService stageService;
    private final Map<Long, ThreadState> threads = new HashMap<>();

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
        ResponseMessage responseMessage;
        if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getFrom().getId();
            String text = update.getCallbackQuery().getData();

            exc(chatId, text);

            Stage stage = stageService.getByChatId(chatId);
            Handler handler = handlerFactory.getHandler(stage);
            responseMessage = handler.handle(chatId, text);
            if (responseMessage.getButtonStage() == Stage.ANSWER) {
                threads.get(chatId).setStop(true);
                responseMessage.setButtonStage(Stage.MAIN);
            }
            stageService.save(chatId, responseMessage.getButtonStage());

            Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
            button.setButton(responseMessage.getSendMessage());

            try {
                execute(responseMessage.getSendMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            exc(chatId, text);

            Stage stage = stageService.getByChatId(chatId);
            if (stage == Stage.PLAY) {
                return;
            }

            Handler handler = handlerFactory.getHandler(stage);
            responseMessage = handler.handle(chatId, text);
            stageService.save(chatId, responseMessage.getButtonStage());

            Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
            button.setButton(responseMessage.getSendMessage());

            try {
                Message message = execute(responseMessage.getSendMessage());
                repeatTimer(responseMessage, message, chatId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void exc(Long chatId, String text) {
        if (text.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText("Возврат");
            stageService.save(chatId, Stage.MAIN);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void repeatTimer(ResponseMessage responseMessage, Message message, Long chatId) throws Exception {
        if (responseMessage.getButtonStage() == Stage.PLAY) {
            EditMessageText editMessage = new EditMessageText();
            editMessage.setChatId(String.valueOf(chatId));
            editMessage.setMessageId(message.getMessageId());
            editMessage.setReplyMarkup((InlineKeyboardMarkup) responseMessage.getSendMessage().getReplyMarkup());
            Thread thread = new Thread(() -> {
                try {
                    for (int i = 9; i >= 0; i--) {
                        if (threads.get(chatId).isStop() || i == 0) {
                            DeleteMessage deleteMessage = new DeleteMessage();
                            deleteMessage.setChatId(String.valueOf(chatId));
                            deleteMessage.setMessageId(message.getMessageId());
                            execute(deleteMessage);
                            stageService.save(chatId, Stage.MAIN);
                            if (i == 0) {
                                SendMessage sendMessage = new SendMessage();
                                sendMessage.setChatId(String.valueOf(chatId));
                                sendMessage.setText("Время истекло");
                                execute(sendMessage);
                            }
                            return;
                        }
                        Thread.sleep(1000L);
                        editMessage.setText(String.valueOf(i));
                        execute(editMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            threads.put(chatId, new ThreadState(thread));
            thread.start();
        }
    }
}
