package ru.gb.telegrambotgateway.service;

public interface ResponseTextService {

    String getCorrectAnswer();

    String getIncorrectAnswer();

    String getTimeOut();

    String getStart();

    String getDailyRating(Long chatId);

    String getWeeklyRating(Long chatId);

    String getMonthlyRating(Long chatId);

    String getTotalRating(Long chatId);

    String getSupport();
}
