package ru.gb.questionapi.parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private List<QuestionWithAnswers> data;
    private Boolean ok;
    private int amount;
}
