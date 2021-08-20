package ru.gb.telegrambotgateway.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambotgateway.button.*;
import ru.gb.telegrambotgateway.model.Stage;

@Component
@RequiredArgsConstructor
public class ButtonFactory {

    private final MainButton mainButton;
    private final PlayButton playButton;
    private final RatingButton ratingButton;
    private final SupportButton supportButton;
    private final TotalRatingButton totalRatingButton;
    private final DailyRatingButton dailyRatingButton;
    private final WeeklyRatingButton weeklyRatingButton;
    private final MonthlyRatingButton monthlyRatingButton;

    public Button getButtons(Stage stage) {

        switch (stage) {
            case MAIN:
            default:
                return mainButton;
            case PLAY:
                return playButton;
            case RATING:
                return ratingButton;
            case SUPPORT:
                return supportButton;
            case TOTAL_RATING:
                return totalRatingButton;
            case DAILY_RATING:
                return dailyRatingButton;
            case WEEKLY_RATING:
                return weeklyRatingButton;
            case MONTHLY_RATING:
                return monthlyRatingButton;
        }
    }
    
}
