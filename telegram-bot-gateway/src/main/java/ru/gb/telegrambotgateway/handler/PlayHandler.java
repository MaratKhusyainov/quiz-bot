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
public class PlayHandler implements Handler {

    private final QuestionService questionService;
    private final ResponseTextService textService;

    @Override
    public ResponseMessage handle(User user, String text) {

        QuestionDto questionDto = questionService.getByChatId(user.getId());

        ResponseMessage responseMessage = getResponseMessage(user.getId());
        if (text.equals(questionDto.getAnswers().get(0))) {
            responseMessage.getSendMessage().setText(textService.getCorrectAnswer());
            questionService.answer(user.getId(), questionDto, true);
        } else {
            responseMessage.getSendMessage().setText(textService.getIncorrectAnswer() + System.lineSeparator() + "Правильный ответ: " + questionDto.getAnswers().get(0));
            questionService.answer(user.getId(), questionDto, false);
        }
        responseMessage.setButtonStage(Stage.ANSWER);

        return responseMessage;
    }

}
