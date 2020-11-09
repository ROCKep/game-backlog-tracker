package ru.otus.gbt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Карточка игры
 */
@Getter
@AllArgsConstructor
public class GameDto {
    private long id;
    private String name;
    private String cover;
    private int releaseYear;
}
