package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.User;
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
}
