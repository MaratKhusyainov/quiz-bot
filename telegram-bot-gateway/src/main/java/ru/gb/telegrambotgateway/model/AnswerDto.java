package ru.gb.telegrambotgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private Long chatId;
    private Long questionId;
    private boolean isCorrect;
}
