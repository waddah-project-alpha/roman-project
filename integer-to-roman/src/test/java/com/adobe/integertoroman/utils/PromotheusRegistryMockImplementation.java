package com.adobe.integertoroman.utils;

import io.prometheus.metrics.model.registry.Collector;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PromotheusRegistryMockImplementation {
    @Bean
    public PrometheusRegistry promotheusRegistry() {
        return new PrometheusRegistry() {
            public void register(Collector collector) {
                // do nothing
            }
        };
    }
}
