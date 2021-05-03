package ru.otus.gbt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
@Getter
@Setter
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
