package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.exceptions.UserNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't found user with id = " + id));
    }

    public User findByChatId(Long id) {
        return userRepository.findByChatId(id);
    }

    public void saveOrUpdate(UserDto userDto) {
        if(userRepository.findByChatId(userDto.getChatId()) == null){
            System.out.println("Такого пользователя нет");
            User user = User.builder()
                    .chatId(userDto.getChatId())
                    .login(userDto.getLogin())
                    .name(userDto.getName())
                    .build();
            userRepository.save(user);
        } else {
            System.out.println("Такой пользователь существует");
        }
    }
}
