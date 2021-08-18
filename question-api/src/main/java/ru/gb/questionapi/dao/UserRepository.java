package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.questionapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
