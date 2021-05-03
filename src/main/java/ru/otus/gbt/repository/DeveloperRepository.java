package ru.otus.gbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gbt.domain.Developer;

import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByName(String name);
}
