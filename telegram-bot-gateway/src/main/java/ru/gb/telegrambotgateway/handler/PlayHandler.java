package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.model.Question;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.QuestionService;

@Component
@RequiredArgsConstructor
public class PlayHandler implements Handler {

    private final QuestionService questionService;

    @Override
    public ResponseMessage handle(Long chatId, String text) {

        Question question = questionService.getByChatId(chatId);

        ResponseMessage responseMessage = getResponseMessage(chatId);
        if (text.equals(question.getAnswer1())) {
            responseMessage.getSendMessage().setText("Правильно!");
        } else {
            responseMessage.getSendMessage().setText("Неправильно, правильный ответ:" + System.lineSeparator() + question.getAnswer1());
        }
        responseMessage.setButtonStage(Stage.ANSWER);
        questionService.answer(chatId);

        return responseMessage;
    }

}
