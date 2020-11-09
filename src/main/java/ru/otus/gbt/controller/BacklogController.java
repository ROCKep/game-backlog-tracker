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
    public List<GameDto> searchGamesInBacklog(@RequestParam String search) {
        return backlogService.searchGamesInBacklog(search);
    }

    @GetMapping("/api/backlog")
    public List<GameDto> listGamesInBacklog() {
        return backlogService.listGamesInBacklog();
    }

    @PostMapping("/api/backlog")
    public void addGameToBacklog(@RequestBody long gameId) {
        backlogService.addGameToBacklog(gameId);
    }

    @DeleteMapping("/api/backlog/{gameId}")
    public void deleteGameFromBacklog(@PathVariable long gameId) {
        backlogService.deleteGameFromBacklog(gameId);
    }
}
