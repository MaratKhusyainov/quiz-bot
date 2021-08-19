package ru.gb.telegrambotgateway.button;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.gb.telegrambotgateway.model.Question;
import ru.gb.telegrambotgateway.service.QuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayButton implements Button {

    private final QuestionService questionService;

    @Override
    public void setButton(SendMessage sendMessage) {
        Question question = questionService.getQuestion(Long.valueOf(sendMessage.getChatId()));
        InlineKeyboardMarkup markup = setQuestionButtons(question);
        sendMessage.setReplyMarkup(markup);
    }

    private InlineKeyboardMarkup setQuestionButtons(Question question) {
        InlineKeyboardButton questionButton = new InlineKeyboardButton();
        questionButton.setText(question.getQuestion());
        questionButton.setCallbackData(question.getQuestion());
        InlineKeyboardButton answer1Button = new InlineKeyboardButton();
        answer1Button.setText(question.getAnswer1());
        answer1Button.setCallbackData(question.getAnswer1());
        InlineKeyboardButton answer2Button = new InlineKeyboardButton();
        answer2Button.setText(question.getAnswer2());
        answer2Button.setCallbackData(question.getAnswer2());
        InlineKeyboardButton answer3Button = new InlineKeyboardButton();
        answer3Button.setText(question.getAnswer3());
        answer3Button.setCallbackData(question.getAnswer3());
        InlineKeyboardButton answer4Button = new InlineKeyboardButton();
        answer4Button.setText(question.getAnswer4());
        answer4Button.setCallbackData(question.getAnswer4());

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        Collections.addAll(buttons, answer1Button, answer2Button, answer3Button, answer4Button);
        Collections.shuffle(buttons);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(questionButton);
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        Collections.addAll(row2, buttons.get(0), buttons.get(1));
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        Collections.addAll(row3, buttons.get(2), buttons.get(3));

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        Collections.addAll(rows, row1, row2, row3);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

}
