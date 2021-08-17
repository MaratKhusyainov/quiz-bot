package ru.gb.telegrambotgateway.button;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class RatingButton implements Button {

    @Override
    public void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup markup = setReplyKeyboard(sendMessage);
        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>() {{
            add(new KeyboardRow() {{ add("День"); add("Неделя"); }});
            add(new KeyboardRow() {{ add("Месяц"); add("Общий"); }});
            add(new KeyboardRow() {{ add("Назад"); }});
        }};
        markup.setKeyboard(keyboardRows);
    }

}
