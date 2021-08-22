package ru.gb.telegrambotgateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMessage {

    private SendMessage sendMessage;
    private Stage buttonStage;
    private boolean executed;

    public ResponseMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }
}
