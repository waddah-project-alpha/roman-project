package com.adobe.integertoroman.metrics;

import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.core.metrics.Histogram;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import org.springframework.stereotype.Component;

@Component
public class IntegerToRomanTelemetryService {

    private final Counter controllerResultCounter;
    private final Histogram controllerDurationHistogram;

    private IntegerToRomanTelemetryService(PrometheusRegistry prometheusRegistry) {
        controllerResultCounter = Counter.builder()
                .name("roman_to_integer_controller_hit")
                .labelNames("success", "failure")
                .withoutExemplars()
                .register(prometheusRegistry);

        controllerDurationHistogram = Histogram.builder()
                .name("roman_to_integer_controller_duration")
                .withoutExemplars()
                .register(prometheusRegistry);
    }

    public void increaseControllerSuccess(String controllerName) {
        controllerResultCounter.labelValues(controllerName, "success").inc();
    }

    public void increaseControllerFailure(String controllerName) {
        controllerResultCounter.labelValues(controllerName, "failure").inc();
    }

    public void registerControllerExecution(double duration) {
        controllerDurationHistogram.observe(duration);
    }
}
