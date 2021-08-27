package ru.gb.telegrambotgateway.button;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.gb.telegrambotgateway.model.QuestionDto;
import ru.gb.telegrambotgateway.service.inter.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayButton implements Button {

    private final QuestionService questionService;
    private final List<String> circles = Arrays.asList("\uD83D\uDD34 ", "\uD83D\uDFE1 ", "\uD83D\uDFE2 ", "\uD83D\uDD35 ");

    @Override
    public void setButton(SendMessage sendMessage) {
        QuestionDto questionDto = questionService.getByChatId(Long.valueOf(sendMessage.getChatId()));
        InlineKeyboardMarkup markup = setQuestionButtons(questionDto);
        sendMessage.setReplyMarkup(markup);
    }

    private InlineKeyboardMarkup setQuestionButtons(QuestionDto questionDto) {
        Collections.shuffle(circles);
        InlineKeyboardButton answer1Button = new InlineKeyboardButton();
        answer1Button.setText(circles.get(0) + questionDto.getAnswers().get(0));
        answer1Button.setCallbackData(questionDto.getAnswers().get(0));
        InlineKeyboardButton answer2Button = new InlineKeyboardButton();
        answer2Button.setText(circles.get(1) + questionDto.getAnswers().get(1));
        answer2Button.setCallbackData(questionDto.getAnswers().get(1));
        InlineKeyboardButton answer3Button = new InlineKeyboardButton();
        answer3Button.setText(circles.get(2) + questionDto.getAnswers().get(2));
        answer3Button.setCallbackData(questionDto.getAnswers().get(2));
        InlineKeyboardButton answer4Button = new InlineKeyboardButton();
        answer4Button.setText(circles.get(3) + questionDto.getAnswers().get(3));
        answer4Button.setCallbackData(questionDto.getAnswers().get(3));

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        Collections.addAll(buttons, answer1Button, answer2Button, answer3Button, answer4Button);
        Collections.shuffle(buttons);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        Collections.addAll(row1, buttons.get(0), buttons.get(1));
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        Collections.addAll(row2, buttons.get(2), buttons.get(3));

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        Collections.addAll(rows, row1, row2);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

}
