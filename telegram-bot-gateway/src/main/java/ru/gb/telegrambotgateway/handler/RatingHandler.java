package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.ResponseTextService;

@Component
@RequiredArgsConstructor
public class RatingHandler implements Handler {

    private final ResponseTextService textService;

    @Override
    public ResponseMessage handle(User user, String text) {
        ResponseMessage responseMessage = getResponseMessage(user.getId());
        switch (text) {
            case "День":
                responseMessage.getSendMessage().setText(textService.getDailyRating(user.getId()));
                responseMessage.setButtonStage(Stage.DAILY_RATING);
                break;
            case "Неделя":
                responseMessage.getSendMessage().setText(textService.getWeeklyRating(user.getId()));
                responseMessage.setButtonStage(Stage.WEEKLY_RATING);
                break;
            case "Месяц":
                responseMessage.getSendMessage().setText(textService.getMonthlyRating(user.getId()));
                responseMessage.setButtonStage(Stage.MONTHLY_RATING);
                break;
            case "Общий":
                responseMessage.getSendMessage().setText(textService.getTotalRating(user.getId()));
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
