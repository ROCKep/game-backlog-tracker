package ru.otus.gbt.service;

import ru.otus.gbt.domain.Platform;

import java.util.List;

public interface PlatformService {
    List<Platform> getOrCreatePlatforms(List<String> platforms);

    List<String> listAllPlatforms();
}
