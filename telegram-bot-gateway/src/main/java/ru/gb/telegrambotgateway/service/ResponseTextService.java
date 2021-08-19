package ru.gb.telegrambotgateway.service;

public class ResponseTextService {

    public String getPlay(Long chatId) {
        return  "Играем для " + chatId + System.lineSeparator() +
            "Играем" + System.lineSeparator() +
            "Играем";
    }

    public String getDailyRating(Long chatId) {
        return  "Рейтинг за день для " + chatId + System.lineSeparator() +
                "Рейтинг за день" + System.lineSeparator() +
                "Рейтинг за день";
    }

    public String getWeeklyRating(Long chatId) {
        return  "Рейтинг за неделю для " + chatId + System.lineSeparator() +
                "Рейтинг за неделю" + System.lineSeparator() +
                "Рейтинг за неделю";
    }

    public String getMonthlyRating(Long chatId) {
        return  "Рейтинг за месяц для " + chatId + System.lineSeparator() +
                "Рейтинг за месяц" + System.lineSeparator() +
                "Рейтинг за месяц";
    }

    public String getTotalRating(Long chatId) {
        return  "Общий рейтинг для " + chatId + System.lineSeparator() +
                "Общий рейтинг" + System.lineSeparator() +
                "Общий рейтинг";
    }

    public String getSupport() {
        return  "Саппорт саппорт" + System.lineSeparator() +
                "Саппорт саппорт" + System.lineSeparator() +
                "Саппорт саппорт";
    }

}
