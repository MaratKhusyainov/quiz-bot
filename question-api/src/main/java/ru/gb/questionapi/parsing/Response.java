package ru.gb.questionapi.parsing;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private List<QuestionWithAnswers> data;
    private Boolean ok;
    private int amount;

}
