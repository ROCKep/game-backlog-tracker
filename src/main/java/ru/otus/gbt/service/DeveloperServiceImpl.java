package ru.otus.gbt.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gbt.domain.Developer;
import ru.otus.gbt.repository.DeveloperRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Override
    @Transactional
    public Developer getOrCreateDeveloper(@NonNull String name) {
        return developerRepository.findByName(name).orElseGet(() -> {
            Developer developer = new Developer(name);
            developer = developerRepository.save(developer);
            return developer;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> listAllDevelopers() {
        return developerRepository.findAll().stream()
                .map(Developer::getName)
                .collect(Collectors.toList());
    }
}
