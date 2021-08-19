package ru.gb.telegrambotgateway.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.handler.*;
import ru.gb.telegrambotgateway.model.Stage;

@Component
@RequiredArgsConstructor
public class HandlerFactory {

    private final MainHandler mainHandler;
    private final PlayHandler playHandler;
    private final RatingHandler ratingHandler;
    private final SupportHandler supportHandler;
    private final TotalRatingHandler totalRatingHandler;
    private final DailyRatingHandler dailyRatingHandler;
    private final WeeklyRatingHandler weeklyRatingHandler;
    private final MonthlyRatingHandler monthlyRatingHandler;

    public Handler getHandler(Stage stage) {

        switch (stage) {
            case MAIN:
            default:
                return mainHandler;
            case PLAY:
                return playHandler;
            case RATING:
                return ratingHandler;
            case SUPPORT:
                return supportHandler;
            case TOTAL_RATING:
                return totalRatingHandler;
            case DAILY_RATING:
                return dailyRatingHandler;
            case WEEKLY_RATING:
                return weeklyRatingHandler;
            case MONTHLY_RATING:
                return monthlyRatingHandler;
        }
    }

}
