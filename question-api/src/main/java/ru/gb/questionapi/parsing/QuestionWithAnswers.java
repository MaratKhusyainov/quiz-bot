package ru.gb.questionapi.parsing;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.util.Objects;

@Data
public class QuestionWithAnswers {

    String question;
    String [] answers;
    int id;

}

