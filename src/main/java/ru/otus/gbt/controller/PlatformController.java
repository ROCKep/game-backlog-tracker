package ru.otus.gbt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.gbt.service.PlatformService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;

    @GetMapping("/api/platforms")
    public List<String> listAllPlatforms() {
        return platformService.listAllPlatforms();
    }
}
