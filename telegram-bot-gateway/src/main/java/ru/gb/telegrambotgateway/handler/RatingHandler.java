package ru.gb.telegrambotgateway.handler;

import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

public class RatingHandler implements Handler {

    @Override
    public ResponseMessage handle(Long chatId, String text) {
        ResponseMessage responseMessage = getResponseMessage(chatId);
        switch (text) {
            case "День":
                responseMessage.getSendMessage().setText(textService.getDailyRating(chatId));
                responseMessage.setButtonStage(Stage.DAILY_RATING);
                break;
            case "Неделя":
                responseMessage.getSendMessage().setText(textService.getWeeklyRating(chatId));
                responseMessage.setButtonStage(Stage.WEEKLY_RATING);
                break;
            case "Месяц":
                responseMessage.getSendMessage().setText(textService.getMonthlyRating(chatId));
                responseMessage.setButtonStage(Stage.MONTHLY_RATING);
                break;
            case "Общий":
                responseMessage.getSendMessage().setText(textService.getTotalRating(chatId));
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
