package ru.otus.gbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gbt.domain.BacklogItem;

import java.util.List;
import java.util.Optional;

public interface BacklogRepository extends JpaRepository<BacklogItem, Long> {
    List<BacklogItem> findByUserId(long userId);
    List<BacklogItem> findByUserIdAndGameNameContaining(long userId, String gameName);
    boolean existsByUserIdAndGameId(long userId, long gameId);
    Optional<BacklogItem> findByUserIdAndGameId(long userId, long gameId);
}
