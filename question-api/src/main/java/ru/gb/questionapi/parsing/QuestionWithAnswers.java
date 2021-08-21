package ru.gb.questionapi.parsing;

import lombok.Data;

@Data
public class QuestionWithAnswers {

    private String question;
    private String [] answers;
    private int id;
}

