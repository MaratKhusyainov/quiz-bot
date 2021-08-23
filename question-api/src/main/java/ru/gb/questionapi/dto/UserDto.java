package ru.gb.questionapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String login;
    private String name;
    private Long chatId;
}
