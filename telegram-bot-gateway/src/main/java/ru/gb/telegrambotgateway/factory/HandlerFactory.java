package ru.gb.telegrambotgateway.factory;

import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.handler.*;
import ru.gb.telegrambotgateway.model.Stage;

@Component
public class HandlerFactory {

    public Handler getHandler(Stage stage) {

        switch (stage) {
            case MAIN:
            default:
                return new MainHandler();
            case PLAY:
                return new PlayHandler();
            case RATING:
                return new RatingHandler();
            case SUPPORT:
                return new SupportHandler();
            case TOTAL_RATING:
                return new TotalRatingHandler();
            case DAILY_RATING:
                return new DailyRatingHandler();
            case WEEKLY_RATING:
                return new WeeklyRatingHandler();
            case MONTHLY_RATING:
                return new MonthlyRatingHandler();
        }
    }

}
