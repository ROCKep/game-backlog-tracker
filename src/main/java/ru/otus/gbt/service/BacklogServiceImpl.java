package ru.otus.gbt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gbt.domain.BacklogItem;
import ru.otus.gbt.domain.Game;
import ru.otus.gbt.domain.User;
import ru.otus.gbt.dto.GameDto;
import ru.otus.gbt.exception.NotFoundException;
import ru.otus.gbt.repository.BacklogRepository;
import ru.otus.gbt.repository.GameRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BacklogServiceImpl implements BacklogService {

    private final BacklogRepository backlogRepository;
    private final GameRepository gameRepository;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<GameDto> listGamesInBacklog() {
        User user = userService.getAuthenticatedUser();
        List<BacklogItem> games = backlogRepository.findByUserId(user.getId());
        return games.stream()
                .map(backlogItem ->
                        new GameDto(backlogItem.getGame().getId(), backlogItem.getGame().getName(), backlogItem.getGame().getCover(), backlogItem.getGame().getReleaseDate().getYear()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameDto> searchGamesInBacklog(@NonNull String search) {
        User user = userService.getAuthenticatedUser();
        List<BacklogItem> games = backlogRepository.findByUserIdAndGameNameContaining(user.getId(), search);
        return games.stream()
                .map(backlogItem ->
                        new GameDto(backlogItem.getGame().getId(), backlogItem.getGame().getName(), backlogItem.getGame().getCover(), backlogItem.getGame().getReleaseDate().getYear()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addGameToBacklog(long gameId) {
        User user = userService.getAuthenticatedUser();
        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new NotFoundException("Игра с id %s не найдена", gameId));
        if (!backlogRepository.existsByUserIdAndGameId(user.getId(), gameId)) {
            BacklogItem newBacklogItem = new BacklogItem(LocalDate.now(), game, user);
            backlogRepository.save(newBacklogItem);
        }
    }

    @Override
    @Transactional
    public void deleteGameFromBacklog(long gameId) {
        User user = userService.getAuthenticatedUser();
        if (!gameRepository.existsById(gameId)) {
            throw new NotFoundException("Игра с id %s не найдена", gameId);
        }
        backlogRepository.findByUserIdAndGameId(user.getId(), gameId)
                .ifPresent(backlogRepository::delete);
    }
}
