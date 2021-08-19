package ru.gb.telegrambotgateway.handler;

import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;

public class MainHandler implements Handler {

    @Override
    public ResponseMessage handle(Long chatId, String text) {
        ResponseMessage responseMessage = getResponseMessage(chatId);
        switch (text) {
            case "Играть":
                responseMessage.getSendMessage().setText(textService.getPlay(chatId));
                responseMessage.setButtonStage(Stage.PLAY);
                break;
            case "Рейтинг":
                responseMessage.getSendMessage().setText("Рейтинг");
                responseMessage.setButtonStage(Stage.RATING);
                break;
            case "Помощь":
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
