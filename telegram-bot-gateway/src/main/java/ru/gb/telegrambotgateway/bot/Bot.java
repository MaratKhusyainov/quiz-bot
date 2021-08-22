package ru.gb.telegrambotgateway.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambotgateway.button.Button;
import ru.gb.telegrambotgateway.factory.ButtonFactory;
import ru.gb.telegrambotgateway.factory.HandlerFactory;
import ru.gb.telegrambotgateway.handler.CommandHandler;
import ru.gb.telegrambotgateway.handler.Handler;
import ru.gb.telegrambotgateway.model.Question;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.model.ThreadState;
import ru.gb.telegrambotgateway.service.QuestionService;
import ru.gb.telegrambotgateway.service.StageService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final HandlerFactory handlerFactory;
    private final ButtonFactory buttonFactory;
    private final StageService stageService;
    private final QuestionService questionService;
    private final CommandHandler commandHandler;
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
        try {
            ResponseMessage responseMessage;
            Long chatId;
            String text;

            if (update.hasCallbackQuery()) {
                chatId = update.getCallbackQuery().getFrom().getId();
                text = update.getCallbackQuery().getData();
                responseMessage = commandHandler.handle(chatId, text);

                if (!responseMessage.isExecuted()) {
                    Stage stage = stageService.getByChatId(chatId);
                    Handler handler = handlerFactory.getHandler(stage);
                    responseMessage = handler.handle(chatId, text);
                }
                threads.get(chatId).setStop(true);

                stageService.save(chatId, responseMessage.getButtonStage());
                Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
                button.setButton(responseMessage.getSendMessage());
            } else {
                chatId = update.getMessage().getChatId();
                text = update.getMessage().getText();
                responseMessage = commandHandler.handle(chatId, text);

                if (responseMessage.isExecuted()) {
                    ThreadState threadState = threads.get(chatId);
                    if (threadState != null) {
                        threadState.setStop(true);
                    }
                } else {
                    Stage stage = stageService.getByChatId(chatId);
                    if (stage == Stage.PLAY) {
                        return;
                    }
                    Handler handler = handlerFactory.getHandler(stage);
                    responseMessage = handler.handle(chatId, text);
                }

                stageService.save(chatId, responseMessage.getButtonStage());
                Button button = buttonFactory.getButtons(responseMessage.getButtonStage());
                button.setButton(responseMessage.getSendMessage());
            }

            if (responseMessage.getButtonStage() == Stage.PLAY) {
                sendQuestion(chatId);
                Message message = execute(responseMessage.getSendMessage());
                repeatTimer(responseMessage, message, chatId);
            } else {
                execute(responseMessage.getSendMessage());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void repeatTimer(ResponseMessage responseMessage, Message message, Long chatId) {
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
                log.error(e.getMessage());
            }

        });
        threads.put(chatId, new ThreadState(thread));
        thread.start();
    }

    private void sendQuestion(Long chatId) throws TelegramApiException {
        Question question = questionService.getQuestion(chatId);
        String questionText = question.getQuestion();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        File img = new File("C:\\Java\\Projects\\quiz-bot\\telegram-bot-gateway\\src\\main\\resources\\1.jpg");
        sendPhoto.setPhoto(new InputFile(img));
        execute(sendPhoto);
    }
}
