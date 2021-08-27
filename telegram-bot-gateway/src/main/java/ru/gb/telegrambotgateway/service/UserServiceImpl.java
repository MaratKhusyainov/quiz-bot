package ru.gb.telegrambotgateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.gb.telegrambotgateway.dto.UserDto;
import ru.gb.telegrambotgateway.service.inter.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Value("${project.host}")
    private String host;

    @Value("${project.questionApi.port}")
    private String port;

    @Override
    public void saveUser(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getUserName(), user.getFirstName());
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/user";
        restTemplate.postForEntity(url, userDto, Integer.class);
    }
}
