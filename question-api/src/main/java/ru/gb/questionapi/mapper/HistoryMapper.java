package ru.gb.questionapi.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.HistoryDto;
import ru.gb.questionapi.dto.UserDto;
import ru.gb.questionapi.services.HistoryService;
import ru.gb.questionapi.services.UserService;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface HistoryMapper {

    HistoryMapper MAPPER = Mappers.getMapper(HistoryMapper.class);

    History toHistory(HistoryDto dto);

    @InheritInverseConfiguration
    HistoryDto fromHistory(History history);
}