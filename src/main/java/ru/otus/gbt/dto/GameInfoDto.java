package ru.otus.gbt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GameInfoDto {
    private long id;
    private String name;
    private String cover;
    private String summary;
    private LocalDate releaseDate;
    private List<String> platforms;
    private String developer;
    private Long backlogItemId;
    private LocalDate addedToBacklogDate;
}
