package ru.gb.questionapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.questionapi.dao.HistoryRepository;
import ru.gb.questionapi.dao.UserRepository;
import ru.gb.questionapi.domain.History;
import ru.gb.questionapi.domain.User;
import ru.gb.questionapi.dto.HistoryDto;
import ru.gb.questionapi.mapper.HistoryMapper;
import ru.gb.questionapi.mapper.UserMapper;

import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserService userService;

    private final HistoryMapper mapper = HistoryMapper.MAPPER;

    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveOrUpdate(HistoryDto historyDto) {
        System.out.println(historyDto);
        User user = userService.findById(historyDto.getUserId());
        History history = History.builder()
                .user(user)
                .questionId(historyDto.getQuestionId())
                .isCorrect(historyDto.getIsCorrect())
                .build();
        historyRepository.save(history);
    }
}
