package ru.practicum.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.HitDto;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final HitRepository hitRepository;
    private final HitMapper hitMapper;

    @Override
    public void saveHit(HitDto hitDto) {
        hitRepository.save(hitMapper.toHit(hitDto));
    }

    @Override
    public List<ViewStats> findHitsByParams(LocalDateTime start, LocalDateTime end, List<String> uris,
                                            Boolean unique
    ) {
        if (start.isAfter(end)) {
            throw new ValidationException("Начало не может быть после конца");
        }

        if (uris != null && !uris.isEmpty()) {
            if (unique) {
                return hitRepository.findDistinctHitsByUris(start, end, uris);
            }
            return hitRepository.findHitsByUris(start, end, uris);
        }
        if (unique) {
            return hitRepository.findDistinctHits(start, end);
        }
        return hitRepository.findHits(start, end);
    }

}
