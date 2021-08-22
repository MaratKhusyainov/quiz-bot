package ru.gb.telegrambotgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto implements Serializable {
    private Long questionId;
    private String question;
    private List<String> answers;
}
