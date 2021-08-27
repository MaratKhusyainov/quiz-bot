package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.QuestionDto;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.inter.QuestionService;
import ru.gb.telegrambotgateway.service.inter.ResponseTextService;

@Component
@RequiredArgsConstructor
public class MainHandler implements Handler {

    private final ResponseTextService textService;
    private final QuestionService questionService;

    @Override
    public ResponseMessage handle(User user, String text) {
        ResponseMessage responseMessage = getResponseMessage(user.getId());
        switch (text) {
            case "Следующий вопрос ⏳":
                QuestionDto questionDto = questionService.getQuestion(user.getId());
                if (questionDto.isCanGetQuestion()) {
                    responseMessage.getSendMessage().setText("Выбери правильный вариант ответа:");
                    responseMessage.setButtonStage(Stage.PLAY);
                } else {
                    responseMessage.getSendMessage().setText("Сегодня ты уже ответил на 20 вопросов. Жду тебя завтра ☺️");
                    responseMessage.setButtonStage(Stage.MAIN);
                }
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
