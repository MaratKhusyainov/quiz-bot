package ru.gb.questionapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingDto {
    private int place;
    private String loginOrName;
    private Long ratingScores;
}
