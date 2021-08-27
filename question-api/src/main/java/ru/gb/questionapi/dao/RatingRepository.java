package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gb.questionapi.domain.Question;
import ru.gb.questionapi.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserId(Long userId);

    String queryGetDailyPlaceNumber =   "SELECT place_number.row_number " +
                                        "FROM " +
                                            "(SELECT id, user_id, daily_rating, " +
                                            "ROW_NUMBER() OVER (ORDER BY daily_rating DESC) AS ROW_NUMBER " +
                                            "FROM quiz_bot.rating) AS place_number " +
                                        "WHERE place_number.user_id = :userId" + "  ;";
    @Query(value = queryGetDailyPlaceNumber, nativeQuery = true)
    Integer findDailyPlaceNumber(@Param("userId") Long id);


    String queryGetWeeklyPlaceNumber =  "SELECT place_number.row_number " +
                                        "FROM " +
                                            "(SELECT id, user_id, weekly_rating, " +
                                            "ROW_NUMBER() OVER (ORDER BY weekly_rating DESC) AS ROW_NUMBER " +
                                            "FROM quiz_bot.rating) AS place_number " +
                                        "WHERE place_number.user_id = :userId" + "  ;";
    @Query(value = queryGetWeeklyPlaceNumber, nativeQuery = true)
    int findWeeklyPlaceNumber(@Param("userId") Long id);


    String queryGetMonthlyPlaceNumber =     "SELECT place_number.row_number " +
                                            "FROM " +
                                                "(SELECT id, user_id, monthly_rating, " +
                                                "ROW_NUMBER() OVER (ORDER BY monthly_rating DESC) AS ROW_NUMBER " +
                                                "FROM quiz_bot.rating) AS place_number " +
                                            "WHERE place_number.user_id = :userId" + "  ;";
    @Query(value = queryGetMonthlyPlaceNumber, nativeQuery = true)
    int findMonthlyPlaceNumber(@Param("userId") Long id);


    String queryGetTotalPlaceNumber =       "SELECT place_number.row_number " +
                                            "FROM " +
                                                "(SELECT id, user_id, total_rating, " +
                                                "ROW_NUMBER() OVER (ORDER BY total_rating DESC) AS ROW_NUMBER " +
                                                "FROM quiz_bot.rating) AS place_number " +
                                            "WHERE place_number.user_id = :userId" + "  ;";
    @Query(value = queryGetTotalPlaceNumber, nativeQuery = true)
    int findTotalPlaceNumber(@Param("userId") Long id);
}
