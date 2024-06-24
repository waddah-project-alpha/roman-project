package com.adobe.integertoroman.metrics;

import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.core.metrics.Histogram;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import org.springframework.stereotype.Component;

/**
 * Utility class the instantiates and registers a Prometheus counter and histogram. It then exposes some methods
 * to invoke these items, which will then be accessible on the Prometheus actuator endpoint, and ultimately potentially
 * in Graphana or other visualization dashboards
 */
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

    /**
     * Increase the controller success counter. Expects a controller name, which can be used to aggregate results per
     * @param controllerName
     */
    public void increaseControllerSuccess(String controllerName) {
        controllerResultCounter.labelValues(controllerName, "success").inc();
    }

    /**
     * Increase the controller failure counter. Expects a controller name, which can be used to aggregate results per
     * @param controllerName
     */
    public void increaseControllerFailure(String controllerName) {
        controllerResultCounter.labelValues(controllerName, "failure").inc();
    }

    /**
     * Registers an execution duration for a controller method
     */
    public void registerControllerExecution(double duration) {
        controllerDurationHistogram.observe(duration);
    }
}
