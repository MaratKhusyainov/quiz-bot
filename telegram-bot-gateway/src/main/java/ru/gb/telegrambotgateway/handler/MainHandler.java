package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.ResponseTextService;

@Component
@RequiredArgsConstructor
public class MainHandler implements Handler {

    private final ResponseTextService textService;

    @Override
    public ResponseMessage handle(User user, String text) {
        ResponseMessage responseMessage = getResponseMessage(user.getId());
        switch (text) {
            case "Следующий вопрос ⏳":
                responseMessage.getSendMessage().setText("30");
                responseMessage.setButtonStage(Stage.PLAY);
                break;
            case "Рейтинг \uD83C\uDFC6":
                responseMessage.getSendMessage().setText("Рейтинг");
                responseMessage.setButtonStage(Stage.RATING);
                break;
            case "Помощь ⁉️":
                responseMessage.getSendMessage().setText(textService.getSupport());
                responseMessage.setButtonStage(Stage.SUPPORT);
                break;
            default:
                responseMessage.getSendMessage().setText("Неверная команда");
                responseMessage.setButtonStage(Stage.MAIN);
        }

        return responseMessage;
    }

}
