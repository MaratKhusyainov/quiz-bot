package ru.gb.telegrambotgateway.factory;

import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.button.Button;
import ru.gb.telegrambotgateway.button.MainButton;
import ru.gb.telegrambotgateway.button.PlayButton;
import ru.gb.telegrambotgateway.button.*;
import ru.gb.telegrambotgateway.model.Stage;

@Component
public class ButtonFactory {
    
    public Button getButtons(Stage stage) {

        switch (stage) {
            case MAIN:
            default:
                return new MainButton();
            case PLAY:
                return new PlayButton();
            case RATING:
                return new RatingButton();
            case SUPPORT:
                return new SupportButton();
            case TOTAL_RATING:
                return new TotalRatingButton();
            case DAILY_RATING:
                return new DailyRatingButton();
            case WEEKLY_RATING:
                return new WeeklyRatingButton();
            case MONTHLY_RATING:
                return new MonthlyRatingButton();
        }
    }
    
}
