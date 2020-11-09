package ru.otus.gbt.service;

import ru.otus.gbt.dto.GameDto;

import java.util.List;

public interface BacklogService {
    List<GameDto> searchGamesInBacklog(String search);

    List<GameDto> listGamesInBacklog();

    void addGameToBacklog(long gameId);

    void deleteGameFromBacklog(long gameId);
}
