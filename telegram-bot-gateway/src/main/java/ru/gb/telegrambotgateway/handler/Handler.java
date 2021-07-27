package ru.gb.telegrambotgateway.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.gb.telegrambotgateway.model.ResponseMessage;

public interface Handler {

    ResponseMessage handle(String chatId, String text);

    default ResponseMessage getResponseMessage(String chatId) {
        ResponseMessage responseMessage = new ResponseMessage(new SendMessage());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        responseMessage.setSendMessage(sendMessage);
        return responseMessage;
    }

}
