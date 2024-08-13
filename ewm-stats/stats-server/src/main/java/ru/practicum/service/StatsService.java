package ru.practicum.service;

import ru.practicum.model.HitDto;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    void saveHit(HitDto hitDto);

    List<ViewStats> findHitsByParams(LocalDateTime start, LocalDateTime end, List<String> uris,
                                     Boolean unique);
}
