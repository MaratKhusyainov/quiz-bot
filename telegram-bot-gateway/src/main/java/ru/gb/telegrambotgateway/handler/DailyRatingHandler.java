package ru.gb.telegrambotgateway.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

@Component
public class DailyRatingHandler implements Handler {

    @Override
    public ResponseMessage handle(User user, String text) {
        ResponseMessage responseMessage = getResponseMessage(user.getId());
        if (text.equals("Назад")) {
            responseMessage.getSendMessage().setText("Возврат");
            responseMessage.setButtonStage(Stage.RATING);
        } else {
            responseMessage.getSendMessage().setText("Неверная команда");
            responseMessage.setButtonStage(Stage.DAILY_RATING);
        }

        return responseMessage;
    }

}
