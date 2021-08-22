package ru.gb.telegrambotgateway.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {
    void saveUser(User user);
}
