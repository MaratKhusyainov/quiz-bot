package ru.gb.telegrambotgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long chatId;
    private String login;
    private String name;
}
