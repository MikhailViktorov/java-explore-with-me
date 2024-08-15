package ru.practicum.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.EndpointHitDto;
import ru.practicum.model.ViewStatsDto;
import ru.practicum.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final HitRepository hitRepository;
    private final StatsMapper statsMapper;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto hit) {
        EndpointHit endpointHitSave = hitRepository.save(statsMapper.toEndpointHit(hit));
        return statsMapper.toEndpointHitDto(endpointHitSave);
    }

    @Override
    public List<ViewStatsDto> findHitsByParams(LocalDateTime start, LocalDateTime end, List<String> uris,
                                               Boolean unique
    ) {
        if (uris == null) {
            uris = Collections.emptyList();
        }
        List<ViewStatsDto> stats;
        if (unique) {
            stats = hitRepository.findAllUnique(start, end, uris);
        } else {
            stats = hitRepository.findAll(start, end, uris);
        }
        return stats;
    }
}
