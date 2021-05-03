package ru.otus.gbt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cover")
    private String cover;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "summary")
    private String summary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "platforms_games", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private List<Platform> platforms;

    @ManyToOne(fetch = FetchType.LAZY)
    private Developer developer;

    public Game(String name, String cover, String summary, LocalDate releaseDate, List<Platform> platforms, Developer developer) {
        this.name = name;
        this.cover = cover;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.developer = developer;
    }
}
