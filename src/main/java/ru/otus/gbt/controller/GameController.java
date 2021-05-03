package ru.otus.gbt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.gbt.dto.GameDto;
import ru.otus.gbt.dto.GameInfoDto;
import ru.otus.gbt.service.GameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/api/games")
    public List<GameDto> listGames() {
        return gameService.listGames();
    }

    @GetMapping("/api/games/search")
    public List<GameDto> searchGames(@RequestParam String search) {
        return gameService.searchGames(search);
    }

    @GetMapping("/api/games/{id}")
    public GameInfoDto getGameInfo(@PathVariable long id) {
        return gameService.getGameInfo(id);
    }

    @PostMapping("/api/games")
    public GameDto addNewGame(@RequestBody GameInfoDto gameInfo) {
        return gameService.addNewGame(gameInfo);
    }

    @PutMapping("/api/games/{id}")
    public void editGame(@PathVariable long id, @RequestBody GameInfoDto gameInfo) {
        gameInfo.setId(id);
        gameService.editGame(gameInfo);
    }

    @DeleteMapping("/api/games/{id}")
    public void deleteGame(@PathVariable long id) {
        gameService.deleteGame(id);
    }
}
