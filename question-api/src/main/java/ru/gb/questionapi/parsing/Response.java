package ru.gb.questionapi.parsing;

import java.util.Arrays;
import java.util.List;

public class Response {
    List<QuestionWithAnswers> questionWithAnswers;

    public void getData() {
        for (int i = 0; i < questionWithAnswers.size(); i++) {
            System.out.println(questionWithAnswers.get(i).getQuestion());
            System.out.println(Arrays.toString(questionWithAnswers.get(i).getAnswers()));
        }
    }

}
