package ru.gb.telegrambotgateway.bot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

//@Component
public class Questions extends TelegramLongPollingBot {

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

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("question");
        button1.setCallbackData("question callback");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("answer 1");
        button2.setCallbackData("answer 1 callback");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("answer 2");
        button3.setCallbackData("answer 2 callback");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("answer 3");
        button4.setCallbackData("answer 3 callback");
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("answer 4");
        button5.setCallbackData("answer 4 callback");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button1);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(button2);
        keyboardButtonsRow2.add(button3);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(button4);
        keyboardButtonsRow3.add(button5);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        markup.setKeyboard(rowList);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("5");
        message.setReplyMarkup(markup);

        try {
            Message responseMessage = execute(message);
            EditMessageText editMessage = new EditMessageText();
            editMessage.setChatId(chatId);
            editMessage.setMessageId(responseMessage.getMessageId());
            for (int i = 4; i > 0; i--) {
                Thread.sleep(1000L);
                editMessage.setText(String.valueOf(i));
                editMessage.setReplyMarkup(markup);
                execute(editMessage);
            }
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(chatId);
            deleteMessage.setMessageId(responseMessage.getMessageId());
            execute(deleteMessage);
            SendMessage end = new SendMessage();
            end.setChatId(chatId);
            end.setText("Time out");
            execute(end);
        } catch (TelegramApiException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
