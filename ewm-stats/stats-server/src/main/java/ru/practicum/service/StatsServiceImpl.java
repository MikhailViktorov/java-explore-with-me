package ru.practicum.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.EndpointHitDto;
import ru.practicum.model.ViewStatsDto;
import ru.practicum.repository.HitRepository;

import java.time.LocalDateTime;
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
        List<ViewStatsDto> stats;
        if (start.isAfter(end)) {
            throw new ValidationException("Начало не может быть после конца");
        }

        if (unique) {
            if (uris == null) {
                stats = hitRepository.findDistinctHits(start, end);
            }
            stats = hitRepository.findDistinctHitsByUris(start, end, uris);
        }
        if (uris == null) {
            stats = hitRepository.findHits(start, end);
        } else {
            stats = hitRepository.findHitsByUris(start, end, uris);
        }
        return stats;
    }
}
