package ru.gb.telegrambotgateway.service.inter;

import ru.gb.telegrambotgateway.model.RatingDto;

public interface RatingService {
    RatingDto getDailyRating(Long chatId);

    RatingDto getMonthlyRating(Long chatId);

    RatingDto getWeeklyRating(Long chatId);

    RatingDto getTotalRating(Long chatId);
}
