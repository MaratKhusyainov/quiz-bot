package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gb.questionapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "SELECT * FROM quiz_bot.user WHERE chat_id = :chatId", nativeQuery = true)
//    User findByChatId(@Param("chatId") Long id);

    User findByChatId(Long id);
}
