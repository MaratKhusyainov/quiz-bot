package ru.gb.questionapi.parsing;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private List<QuestionWithAnswers> data;
    private Boolean ok;
    private float amount;
}
