package ru.otus.gbt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users_backlog")
@Getter
@Setter
@NoArgsConstructor
public class BacklogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "added_date")
    private LocalDate addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public BacklogItem(LocalDate addedDate, Game game, User user) {
        this.addedDate = addedDate;
        this.game = game;
        this.user = user;
    }
}
