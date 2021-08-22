package ru.gb.telegrambotgateway.service;

public interface ResponseTextService {

    String getStart();

    String getDailyRating(Long chatId);

    String getWeeklyRating(Long chatId);

    String getMonthlyRating(Long chatId);

    String getTotalRating(Long chatId);

    String getSupport();
}