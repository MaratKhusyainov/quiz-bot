package ru.gb.questionapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "login", target = "login"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "chatId", target = "chatId")
//
//    })

    User toUser(UserDto dto);

    @InheritInverseConfiguration
    UserDto fromUser (User user);
}
