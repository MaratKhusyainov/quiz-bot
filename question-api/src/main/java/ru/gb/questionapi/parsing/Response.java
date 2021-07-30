package ru.gb.questionapi.parsing;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    List<QuestionWithAnswers> data;
    Boolean ok;
    int amount;

    public void getData() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getQuestion());
            System.out.println(Arrays.toString(data.get(i).getAnswers()));
        }
//        System.out.println("test");
    }
}
