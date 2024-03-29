package com.rbkmoney.dominant.cache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.rbkmoney.damsel.domain_config.RepositorySrv;
import com.rbkmoney.damsel.domain_config.Snapshot;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class ApplicationConfig {

    @Bean
    public RepositorySrv.Iface dominantClient(
            @Value("${dominant.url}") Resource resource,
            @Value("${dominant.networkTimeout}") int networkTimeout
    ) throws IOException {
        return new THSpawnClientBuilder()
                .withNetworkTimeout(networkTimeout)
                .withAddress(resource.getURI()).build(RepositorySrv.Iface.class);
    }

    @Bean
    public Cache<String, Snapshot> cache(@Value("${cache.maxSize}") long cacheMaximumSize) {
        return Caffeine.newBuilder()
                .maximumSize(cacheMaximumSize)
                .build();
    }

}
