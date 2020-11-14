package ru.otus.gbt.service;

import ru.otus.gbt.dto.GameDto;

import java.util.List;

public interface BacklogService {
    List<GameDto> searchGamesInBacklogOfCurrentUser(String search);

    List<GameDto> listGamesInBacklogOfCurrentUser();

    void addGameToBacklogOfCurrentUser(long gameId);

    void deleteGameFromBacklogOfCurrentUser(long gameId);
}
