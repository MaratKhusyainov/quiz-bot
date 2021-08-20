package ru.gb.questionapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.questionapi.domain.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
