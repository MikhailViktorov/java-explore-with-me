package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT (h.ip))"
            + "FROM EndpointHit AS h "
            + "WHERE h.timestamp BETWEEN ?1 and ?2 "
            + "GROUP BY h.uri, h.app "
            + "ORDER BY COUNT (h.ip) DESC ")
    List<ViewStats> findHits(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, count(h.ip)) "
            + "FROM EndpointHit AS h "
            + "WHERE h.timestamp BETWEEN ?1 AND ?2 "
            + "GROUP BY h.uri, h.app "
            + "ORDER BY COUNT(h.ip) DESC ")
    List<ViewStats> findDistinctHits(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, count(distinct h.ip)) "
            + "FROM EndpointHit h "
            + "WHERE h.timestamp BETWEEN ?1 AND ?2 "
            + "AND h.uri IN ?3 "
            + "GROUP BY h.uri, h.app "
            + "ORDER BY COUNT (h.ip) DESC ")
    List<ViewStats> findDistinctHitsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT (h.ip))"
            + "FROM EndpointHit h "
            + "WHERE h.timestamp BETWEEN ?1 AND ?2 "
            + "AND h.uri in ?3 "
            + "GROUP BY h.uri, h.app "
            + "ORDER BY COUNT (h.ip) DESC ")
    List<ViewStats> findHitsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);


}
