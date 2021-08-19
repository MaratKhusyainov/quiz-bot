package ru.gb.telegrambotgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private Long questionId;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
}
