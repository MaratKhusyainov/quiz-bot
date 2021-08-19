package ru.gb.telegrambotgateway.handler;

import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

public class WeeklyRatingHandler implements Handler {

    @Override
    public ResponseMessage handle(Long chatId, String text) {
        ResponseMessage responseMessage = getResponseMessage(chatId);
        if (text.equals("Назад")) {
            responseMessage.getSendMessage().setText("Возврат");
            responseMessage.setButtonStage(Stage.RATING);
        } else {
            responseMessage.getSendMessage().setText("Неверная команда");
            responseMessage.setButtonStage(Stage.WEEKLY_RATING);
        }

        return responseMessage;
    }

}
