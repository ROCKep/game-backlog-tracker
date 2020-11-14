package ru.otus.gbt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.gbt.dto.GameDto;
import ru.otus.gbt.service.BacklogService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BacklogController {

    private final BacklogService backlogService;

    @GetMapping("/api/backlog/search")
    public List<GameDto> searchGamesInBacklogOfCurrentUser(@RequestParam String search) {
        return backlogService.searchGamesInBacklogOfCurrentUser(search);
    }

    @GetMapping("/api/backlog")
    public List<GameDto> listGamesInBacklogOfCurrentUser() {
        return backlogService.listGamesInBacklogOfCurrentUser();
    }

    @PostMapping("/api/backlog")
    public void addGameToBacklogOfCurrentUser(@RequestBody long gameId) {
        backlogService.addGameToBacklogOfCurrentUser(gameId);
    }

    @DeleteMapping("/api/backlog/{gameId}")
    public void deleteGameFromBacklogOfCurrentUser(@PathVariable long gameId) {
        backlogService.deleteGameFromBacklogOfCurrentUser(gameId);
    }
}
