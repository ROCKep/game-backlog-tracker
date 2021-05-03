package ru.otus.gbt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.gbt.service.DeveloperService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping("/api/developers")
    public List<String> listAllDevelopers() {
        return developerService.listAllDevelopers();
    }

}
