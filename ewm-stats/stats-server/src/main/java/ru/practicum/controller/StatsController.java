package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.EndpointHitDto;
import ru.practicum.model.ViewStats;
import ru.practicum.model.ViewStatsDto;
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
        log.info("POST /hit: endpoint={}", endpointHitDto);
        return ResponseEntity.ok().body(statsService.saveHit(endpointHitDto));
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsDto>> getStats(
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false, defaultValue = "false") Boolean unique
    ) {
        return ResponseEntity.ok().body(statsService.findHitsByParams(start, end, uris, unique));
    }
}
