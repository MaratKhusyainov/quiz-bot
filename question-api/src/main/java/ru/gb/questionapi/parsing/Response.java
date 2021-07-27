package ru.gb.questionapi.parsing;

import java.util.Arrays;
import java.util.List;

public class Response {
    List<Data> data;

    public void getData() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getQuestion());
            System.out.println(Arrays.toString(data.get(i).getAnswers()));
        }
    }

}
