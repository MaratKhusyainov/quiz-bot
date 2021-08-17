package ru.gb.telegrambotgateway.handler;

import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

public class RatingHandler implements Handler {

    @Override
    public ResponseMessage handle(String chatId, String text) {
        ResponseMessage responseMessage = getResponseMessage(chatId);
        switch (text) {
            case "День":
                responseMessage.getSendMessage().setText("Рейтинг за день:");
                responseMessage.setButtonStage(Stage.DAILY_RATING);
                break;
            case "Неделя":
                responseMessage.getSendMessage().setText("Рейтинг за неделю");
                responseMessage.setButtonStage(Stage.WEEKLY_RATING);
                break;
            case "Месяц":
                responseMessage.getSendMessage().setText("Рейтинг за месяц");
                responseMessage.setButtonStage(Stage.MONTHLY_RATING);
                break;
            case "Общий":
                responseMessage.getSendMessage().setText("Общий рейтинг");
                responseMessage.setButtonStage(Stage.TOTAL_RATING);
                break;
            case "Назад":
                responseMessage.getSendMessage().setText("Возврат в главное меню");
                responseMessage.setButtonStage(Stage.MAIN);
                break;
            default:
                responseMessage.getSendMessage().setText("Неверная команда");
                responseMessage.setButtonStage(Stage.RATING);
        }

        return responseMessage;
    }

}
