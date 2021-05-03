package ru.otus.gbt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gbt.domain.Platform;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    List<Platform> findByNameIn(List<String> names);
}
