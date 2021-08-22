package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.ResponseTextService;

@Component
@RequiredArgsConstructor
public class CommandHandler implements Handler {

    private final ResponseTextService textService;

    @Override
    public ResponseMessage handle(Long chatId, String text) {
        ResponseMessage responseMessage = getResponseMessage(chatId);
        switch (text) {
            case "/start":
                responseMessage.setExecuted(true);
                responseMessage.getSendMessage().setText(textService.getStart());
                responseMessage.setButtonStage(Stage.MAIN);
                break;
            case "/restore":
                responseMessage.setExecuted(true);
                responseMessage.getSendMessage().setText("Восстанавливаем");
                responseMessage.setButtonStage(Stage.MAIN);
                break;
        }

        return responseMessage;
    }

}
