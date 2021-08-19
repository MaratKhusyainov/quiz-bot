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
            responseMessage.setButtonStage(Stage.ANSWER);
            questionService.answer(chatId);
        } else if (text.equals(question.getAnswer2()) ||
                text.equals(question.getAnswer3()) ||
                text.equals(question.getAnswer4())) {
            responseMessage.getSendMessage().setText("Неправильно, правильный ответ:" + System.lineSeparator() + question.getAnswer1());
            responseMessage.setButtonStage(Stage.ANSWER);
            questionService.answer(chatId);
        } else if (text.equals("Назад")) {
            responseMessage.getSendMessage().setText("Возврат");
            responseMessage.setButtonStage(Stage.MAIN);
        } else {
            responseMessage.getSendMessage().setText("Необходимо ответить на вопрос");
            responseMessage.setButtonStage(Stage.PLAY);
        }

        return responseMessage;
    }

}
