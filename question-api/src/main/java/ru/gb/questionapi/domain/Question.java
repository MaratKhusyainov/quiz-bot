package ru.gb.questionapi.domain;

import lombok.*;
import ru.gb.questionapi.parsing.QuestionWithAnswers;

import javax.persistence.*;


@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question extends QuestionWithAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id1;

    @Column(name = "question")
    private String question;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4")
    private String answer4;

    @Column(name = "complexity")
    private int complexity;

    @Column(name = "hash")
    private int hash;
}
