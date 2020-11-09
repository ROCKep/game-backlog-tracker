package ru.otus.gbt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gbt.domain.Platform;
import ru.otus.gbt.repository.PlatformRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public List<Platform> getOrCreatePlatforms(@NonNull List<String> names) {
        List<Platform> platforms = platformRepository.findByNameIn(names);
        if (platforms.size() != names.size()) {
            List<String> platformNames = platforms.stream()
                    .map(Platform::getName)
                    .collect(toList());
            List<Platform> newPlatforms = names.stream()
                    .filter(name -> !platformNames.contains(name))
                    .map(Platform::new)
                    .collect(toList());
            newPlatforms = platformRepository.saveAll(newPlatforms);
            platforms.addAll(newPlatforms);
        }
        return platforms;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> listAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(Platform::getName)
                .collect(toList());
    }
}
