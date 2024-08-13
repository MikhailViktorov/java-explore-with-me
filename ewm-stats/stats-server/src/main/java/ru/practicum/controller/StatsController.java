package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.EndpointHitDto;
import ru.practicum.model.ViewStats;
import ru.practicum.service.StatsService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class StatsController {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final StatsService statsService;


    @PostMapping("/hit")
    public ResponseEntity<EndpointHitDto> saveHit(@Valid @RequestBody EndpointHitDto endpointHitDto) {
        return new ResponseEntity<>(statsService.saveHit(endpointHitDto), HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStats>> getStats(
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false, defaultValue = "false") Boolean unique
    ) {
        return ResponseEntity.ok().body(statsService.findHitsByParams(start, end, uris, unique));
    }
}
