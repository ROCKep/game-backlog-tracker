package ru.otus.gbt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gbt.domain.Developer;
import ru.otus.gbt.domain.Game;
import ru.otus.gbt.domain.Platform;
import ru.otus.gbt.domain.User;
import ru.otus.gbt.dto.GameDto;
import ru.otus.gbt.dto.GameInfoDto;
import ru.otus.gbt.exception.NotFoundException;
import ru.otus.gbt.repository.BacklogRepository;
import ru.otus.gbt.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlatformService platformService;
    private final DeveloperService developerService;
    private final BacklogRepository backlogRepository;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<GameDto> listGames() {

        List<Game> games = gameRepository.findAll();

        return games.stream()
                .map(game ->
                        new GameDto(game.getId(), game.getName(), game.getCover(), game.getReleaseDate().getYear()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GameInfoDto getGameInfo(long id) {
        User user = userService.getAuthenticatedUser();
        GameInfoDto gameInfo = gameRepository.findById(id).map(game -> new GameInfoDto(
                game.getId(),
                game.getName(),
                game.getCover(),
                game.getSummary(),
                game.getReleaseDate(),
                game.getPlatforms().stream()
                        .map(Platform::getName)
                        .collect(Collectors.toList()),
                game.getDeveloper().getName(),
                null, null
        )).orElseThrow(() -> new NotFoundException("Игра с id %s не найдена", id));
        backlogRepository.findByUserIdAndGameId(user.getId(), id).ifPresent(backlogItem -> {
            gameInfo.setBacklogItemId(backlogItem.getId());
            gameInfo.setAddedToBacklogDate(backlogItem.getAddedDate());
        });
        return gameInfo;
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public GameDto addNewGame(@NonNull GameInfoDto gameInfo) {
        List<Platform> platforms = platformService.getOrCreatePlatforms(gameInfo.getPlatforms());
        Developer developer = developerService.getOrCreateDeveloper(gameInfo.getDeveloper());
        Game newGame = new Game(gameInfo.getName(), gameInfo.getCover(), gameInfo.getSummary(), gameInfo.getReleaseDate(), platforms, developer);
        newGame = gameRepository.save(newGame);
        return new GameDto(newGame.getId(), newGame.getName(), newGame.getCover(), newGame.getReleaseDate().getYear());
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public void deleteGame(long id) {
        gameRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADMIN"})
    public void editGame(@NonNull GameInfoDto gameInfo) {
        Game game = gameRepository.findById(gameInfo.getId()).orElseThrow(() ->
                new NotFoundException("Игра с id %s не найдена", gameInfo.getId()));
        game.setName(gameInfo.getName());
        game.setCover(gameInfo.getCover());
        game.setSummary(gameInfo.getSummary());
        game.setReleaseDate(gameInfo.getReleaseDate());
        game.setPlatforms(platformService.getOrCreatePlatforms(gameInfo.getPlatforms()));
        game.setDeveloper(developerService.getOrCreateDeveloper(gameInfo.getDeveloper()));
        gameRepository.save(game);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameDto> searchGames(@NonNull String search) {
        List<Game> games = gameRepository.findByNameContaining(search);
        return games.stream()
                .map(game ->
                        new GameDto(game.getId(), game.getName(), game.getCover(), game.getReleaseDate().getYear()))
                .collect(Collectors.toList());
    }
}
