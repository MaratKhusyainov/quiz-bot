package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.exceptions.UserNotFoundException;
import ru.gb.questionapi.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper mapper = UserMapper.MAPPER;

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
            User user = mapper.toUser(userDto);
            userRepository.save(user);
        }
    }
}
