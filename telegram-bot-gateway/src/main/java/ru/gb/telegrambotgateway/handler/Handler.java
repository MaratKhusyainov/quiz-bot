package ru.gb.telegrambotgateway.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.ResponseMessage;

@Component
public interface Handler {

    ResponseMessage handle(User user, String text);

    default ResponseMessage getResponseMessage(Long chatId) {
        ResponseMessage responseMessage = new ResponseMessage(new SendMessage());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        responseMessage.setSendMessage(sendMessage);
        return responseMessage;
    }

}
