package ru.gb.questionapi.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class HistoryDto {
    private Long userId;
    private Long questionId;
    private boolean isCorrect;

    public boolean getIsCorrect() {
        return isCorrect;
    }


}
