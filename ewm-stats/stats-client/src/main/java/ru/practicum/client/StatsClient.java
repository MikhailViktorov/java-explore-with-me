package ru.practicum.client;


import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsClient extends BaseClient {
    public StatsClient(RestTemplateBuilder builder, String serverUrl) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build());
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end,
                                           @Nullable List<String> uris, boolean unique) {


        Map<String, Object> parameters = new HashMap<>(Map.of("start", start,
                "end", end,
                "unique", unique));

        if (uris != null) {
            parameters.put("uris", uris);
        }

        return get("/stat" + "?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }


}
