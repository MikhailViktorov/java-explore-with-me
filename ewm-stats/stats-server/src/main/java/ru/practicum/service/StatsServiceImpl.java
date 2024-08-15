package ru.practicum.service;

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
        if (Boolean.TRUE.equals(unique)) {
            return hitRepository.findAllUnique(start, end, uris);
        } else {
            return hitRepository.findAll(start, end, uris);
        }
    }
}
