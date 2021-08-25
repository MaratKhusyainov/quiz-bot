package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.RatingRepository;
import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.Rating;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.RatingDto;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;

    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Rating findByUserId(Long userId){
        return ratingRepository.findByUserId(userId);
    }

    public RatingDto findUserDailyRatingByChatId(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        Rating rating = ratingRepository.findByUserId(user.getId());
        int placeNumber = ratingRepository.findDailyPlaceNumber(user.getId());

        return RatingDto.builder()
                .loginOrName(chooserLoginOrName(user))
                .ratingScores(rating.getDailyRating())
                .place(placeNumber)
                .build();
    }

    public RatingDto findUserWeeklyRatingByChatId(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        Rating rating = ratingRepository.findByUserId(user.getId());
        int placeNumber = ratingRepository.findWeeklyPlaceNumber(user.getId());

        return RatingDto.builder()
                .loginOrName(chooserLoginOrName(user))
                .ratingScores(rating.getWeeklyRating())
                .place(placeNumber)
                .build();
    }

    public RatingDto findUserMonthlyRatingByChatId(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        Rating rating = ratingRepository.findByUserId(user.getId());
        int placeNumber = ratingRepository.findMonthlyPlaceNumber(user.getId());

        return RatingDto.builder()
                .loginOrName(chooserLoginOrName(user))
                .ratingScores(rating.getMonthlyRating())
                .place(placeNumber)
                .build();
    }

    public RatingDto findUserTotalRatingByChatId(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        Rating rating = ratingRepository.findByUserId(user.getId());
        int placeNumber = ratingRepository.findTotalPlaceNumber(user.getId());

        return RatingDto.builder()
                .loginOrName(chooserLoginOrName(user))
                .ratingScores(rating.getTotalRating())
                .place(placeNumber)
                .build();
    }

    public String chooserLoginOrName(User user){
        if (user.getLogin() == null) {
            return user.getName();
        } else return user.getLogin();
    }
}
