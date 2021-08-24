package ru.gb.telegrambotgateway.button;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportButton implements Button {

    @Override
    public void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup markup = setReplyKeyboard(sendMessage);
        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>() {{
            add(new KeyboardRow() {{ add("Назад ↩️"); }});
        }};
        markup.setKeyboard(keyboardRows);
    }

}
