package ru.otus.gbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gbt.domain.Game;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByNameContaining(String name);
}
