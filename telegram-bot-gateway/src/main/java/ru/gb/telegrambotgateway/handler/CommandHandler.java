package ru.gb.telegrambotgateway.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.model.ResponseMessage;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.service.inter.ResponseTextService;
import ru.gb.telegrambotgateway.service.inter.UserService;

@Component
@RequiredArgsConstructor
public class CommandHandler implements Handler {

    private final ResponseTextService textService;
    private final UserService userService;

    @Override
    public ResponseMessage handle(User user, String text) {
        ResponseMessage responseMessage = getResponseMessage(user.getId());
        switch (text) {
            case "/start":
                responseMessage.setExecuted(true);
                responseMessage.getSendMessage().setText(textService.getStart());
                responseMessage.setButtonStage(Stage.MAIN);
                userService.saveUser(user);
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
