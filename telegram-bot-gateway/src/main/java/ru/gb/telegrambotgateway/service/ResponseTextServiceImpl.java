package ru.gb.telegrambotgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.model.RatingDto;
import ru.gb.telegrambotgateway.service.inter.RatingService;
import ru.gb.telegrambotgateway.service.inter.ResponseTextService;

@Component
@RequiredArgsConstructor
public class ResponseTextServiceImpl implements ResponseTextService {

    private final RatingService ratingService;

    private final String[] correctAnswer = {"И это правильный ответ! \uD83D\uDD25", "Абсолютно верно! \uD83D\uDC4D", "Правильно! \uD83E\uDD17", "Именно так! ☀️", "Молодец! \uD83E\uDD47"};
    private final String[] incorrectAnswer = {"К сожалению, нет \uD83D\uDC4E", "Неправильно ❌", "Жаль, но ответ неверный \uD83D\uDE14", "Нет, но не расстраивайся! \uD83D\uDE43", "Увы и ах \uD83D\uDE27"};
    private final String[] timeOut = {"Увы, время вышло ⌛️", "Время истекло \uD83E\uDD7A", "Не успел \uD83D\uDD5D", "А уже всё \uD83D\uDE2C"};

    @Override
    public String getCorrectAnswer() {
        return correctAnswer[(int) (Math.random() * correctAnswer.length)];
    }

    @Override
    public String getIncorrectAnswer() {
        return incorrectAnswer[(int) (Math.random() * incorrectAnswer.length)];
    }

    @Override
    public String getTimeOut() {
        return timeOut[(int) (Math.random() * timeOut.length)];
    }

    @Override
    public String getStart() {
        return
                "Привет, начинающий эрудит! ✌️\n\n" +
                "Коротко о правилах:\n" +
                "\uD83D\uDD38Каждый день тебе будет даваться возможность проверить свои знания в виде 20 вопросов! \uD83D\uDCAA\n" +
                "\uD83D\uDD38Если ты будешь правильно отвечать на вопросы, твой рейтинг будет расти. И это здорово \uD83D\uDD1D\n" +
                "\uD83D\uDD38На каждый вопрос даётся 20 секунд, но не переживай, если ты не успеешь ответить \uD83D\uDD70\n" +
                "\uD83D\uDD38В случае нахождения ошибок и неточностей, ты можешь смело обращаться к команде разработчиков, контакты которой указаны в разделе \"Помощь ⁉️\"\n" +
                "\uD83D\uDD38Не тяни, берись за свой \"следующий вопрос\"! \uD83C\uDF1F";
    }

    @Override
    public String getDailyRating(Long chatId) {
        RatingDto ratingDto = ratingService.getDailyRating(chatId);
        return  "\uD83D\uDFE3 Ты на " + ratingDto.getPlace() + " месте в сегодняшнем рейтинге" + System.lineSeparator() +
                "\uD83D\uDFE3 Сегодня ты набрал " + ratingDto.getRatingScores() + " очков";
    }

    @Override
    public String getWeeklyRating(Long chatId) {
        RatingDto ratingDto = ratingService.getWeeklyRating(chatId);
        return  "\uD83D\uDFE0 Ты на " + ratingDto.getPlace() + " месте в недельном рейтинге" + System.lineSeparator() +
                "\uD83D\uDFE0 На текущей неделе ты набрал " + ratingDto.getRatingScores() + " очков";
    }

    @Override
    public String getMonthlyRating(Long chatId) {
        RatingDto ratingDto = ratingService.getMonthlyRating(chatId);
        return  "\uD83D\uDFE2 Ты на " + ratingDto.getPlace() + " месте в месячном рейтинге" + System.lineSeparator() +
                "\uD83D\uDFE2 В текущем месяце ты набрал " + ratingDto.getRatingScores() + " очков";
    }

    @Override
    public String getTotalRating(Long chatId) {
        RatingDto ratingDto = ratingService.getTotalRating(chatId);
        return  "\uD83D\uDD35 Ты на " + ratingDto.getPlace() + " месте в общем рейтинге" + System.lineSeparator() +
                "\uD83D\uDD35 Всего ты набрал " + ratingDto.getRatingScores() + " очков";
    }

    @Override
    public String getSupport() {
        return  "Если бот завис - ты можешь его восстановить с помощью команды /restore\n\n" +
                "По добавлению новых вопросов, поддержке, нахождению ошибок не стесняйся и пиши команде разработчиков:\n" +
                "@mxpnk\n" +
                "@iura_so_cute\n" +
                "@just_mar1";
    }

}
