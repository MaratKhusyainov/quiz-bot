package ru.gb.telegrambotgateway.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.telegrambotgateway.model.Stage;
import ru.gb.telegrambotgateway.repository.RedisRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RedisRepository redisRepository;


    @Override
    public Stage getByChatId(Long chatId) {
        return redisRepository.getByChatId(chatId);
    }

    @Override
    public void save(Long chatId, Stage stage) {
        redisRepository.save(chatId, stage);
    }
}


