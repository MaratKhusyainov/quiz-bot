package ru.gb.questionapi.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message){super(message);}
}
