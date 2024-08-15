package ru.practicum.service;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.EndpointHitDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StatsMapper {
    EndpointHit toEndpointHit(EndpointHitDto endpointHitDto);

    EndpointHitDto toEndpointHitDto(EndpointHit endpointHit);


}
