package ru.gb.telegrambotgateway.service;

import org.springframework.stereotype.Component;

@Component
public class ResponseTextServiceImpl implements ResponseTextService {

    private final String[] correctAnswer = {"И это правильный ответ! \uD83D\uDD25", "Абсолютно верно! \uD83D\uDC4D", "Правильно! \uD83E\uDD17", "Именно так! ☀️", "Молодец! \uD83E\uDD47"};
    private final String[] incorrectAnswer = {"К сожалению, нет \uD83D\uDC4E", "Неправильно ❌", "Жаль, но ответ неверный \uD83D\uDE14", "Нет, но не расстраивайся! \uD83D\uDE43"};

    @Override
    public String getCorrectAnswer() {
        return correctAnswer[(int) (Math.random() * correctAnswer.length)];
    }

    @Override
    public String getIncorrectAnswer() {
        return incorrectAnswer[(int) (Math.random() * incorrectAnswer.length)];
    }

    @Override
    public String getStart() {
        return "Привет!";
    }

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
