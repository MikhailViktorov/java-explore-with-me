package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.Hit;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(value = "select h.app, h.uri, count(h.*) as hits from hits h "
            + "where timestamp between ?1 and ?2 "
            + "group by h.uri, h.app "
            + "order by count(h.*) desc", nativeQuery = true)
    List<ViewStats> findHits(LocalDateTime start, LocalDateTime end);

    @Query(value = "select h.app, h.uri, count(distinct h.ip) as hits from hits h "
            + "where timestamp between ?1 and ?2 "
            + "group by h.uri, h.app "
            + "order by count(distinct h.ip) desc", nativeQuery = true)
    List<ViewStats> findDistinctHits(LocalDateTime start, LocalDateTime end);

    @Query(value = "select h.app, h.uri, count(distinct h.ip) as hits from hits h "
            + "where timestamp between ?1 and ?2 "
            + "and h.uri in (?3) "
            + "group by h.uri, h.app "
            + "order by count(distinct h.ip)", nativeQuery = true)
    List<ViewStats> findDistinctHitsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "select h.app, h.uri, count(h.*) as hits from hits h "
            + "where timestamp between ?1 and ?2 "
            + "and h.uri in (?3) "
            + "group by h.uri, h.app "
            + "order by count(h.*) desc", nativeQuery = true)
    List<ViewStats> findHitsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);
}
