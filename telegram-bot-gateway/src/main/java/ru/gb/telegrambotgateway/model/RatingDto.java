package ru.gb.telegrambotgateway.model;

import lombok.Data;

@Data
public class RatingDto {
    Long place;
    String loginOrName;
    Long ratingScores;
}
