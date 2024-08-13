package ru.practicum.service;

import org.mapstruct.Mapper;
import ru.practicum.model.Hit;
import ru.practicum.model.HitDto;

@Mapper(componentModel = "spring")
public interface HitMapper {
    Hit toHit(HitDto hitDto);

    HitDto toHitDto(Hit hit);
}
