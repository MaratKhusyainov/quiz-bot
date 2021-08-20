package ru.gb.telegrambotgateway.service;

import org.springframework.stereotype.Component;

@Component
public class ResponseTextServiceImpl implements ResponseTextService {

    @Override
    public String getDailyRating(Long chatId) {
        return  "Рейтинг за день для " + chatId + System.lineSeparator() +
                "Рейтинг за день" + System.lineSeparator() +
                "Рейтинг за день";
    }

    @Override
    public String getWeeklyRating(Long chatId) {
        return  "Рейтинг за неделю для " + chatId + System.lineSeparator() +
                "Рейтинг за неделю" + System.lineSeparator() +
                "Рейтинг за неделю";
    }

    @Override
    public String getMonthlyRating(Long chatId) {
        return  "Рейтинг за месяц для " + chatId + System.lineSeparator() +
                "Рейтинг за месяц" + System.lineSeparator() +
                "Рейтинг за месяц";
    }

    @Override
    public String getTotalRating(Long chatId) {
        return  "Общий рейтинг для " + chatId + System.lineSeparator() +
                "Общий рейтинг" + System.lineSeparator() +
                "Общий рейтинг";
    }

    @Override
    public String getSupport() {
        return  "Саппорт саппорт" + System.lineSeparator() +
                "Саппорт саппорт" + System.lineSeparator() +
                "Саппорт саппорт";
    }

}
