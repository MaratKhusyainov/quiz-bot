package ru.gb.questionapi.parsing;


import lombok.Data;

@Data
public class QuestionWithAnswers {
    String question;
    String [] answers;
}
