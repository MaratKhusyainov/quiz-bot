package ru.gb.questionapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.questionapi.domain.Rating;
import ru.gb.questionapi.dto.RatingDto;
import ru.gb.questionapi.services.QuestionService;
import ru.gb.questionapi.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/daily/{chatId}")
    public RatingDto getDailyRating (@PathVariable Long chatId){
        return ratingService.findUserDailyRatingByChatId(chatId);
    }

    @GetMapping("/weekly/{chatId}")
    public RatingDto getWeeklyRating (@PathVariable Long chatId){
        return ratingService.findUserWeeklyRatingByChatId(chatId);
    }

    @GetMapping("/monthly/{chatId}")
    public RatingDto getMonthlyRating (@PathVariable Long chatId){
        return ratingService.findUserMonthlyRatingByChatId(chatId);
    }

    @GetMapping("/total/{chatId}")
    public RatingDto getTotalRating (@PathVariable Long chatId){
        return ratingService.findUserTotalRatingByChatId(chatId);
    }
}
