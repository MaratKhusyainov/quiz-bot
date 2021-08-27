package ru.gb.questionapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {
    private Long questionId;
    private String question;
    private String[] answers;
    boolean canGetQuestion;
}
