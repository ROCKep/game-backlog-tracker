package ru.otus.gbt.service;

import ru.otus.gbt.dto.GameDto;
import ru.otus.gbt.dto.GameInfoDto;

import java.util.List;

public interface GameService {

    List<GameDto> listGames();

    GameInfoDto getGameInfo(long id);

    GameDto addNewGame(GameInfoDto gameInfo);

    void deleteGame(long id);

    void editGame(GameInfoDto gameInfo);

    List<GameDto> searchGames(String search);
}
