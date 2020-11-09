package ru.otus.gbt.service;

import ru.otus.gbt.domain.Developer;

import java.util.List;

public interface DeveloperService {
    Developer getOrCreateDeveloper(String name);

    List<String> listAllDevelopers();
}
