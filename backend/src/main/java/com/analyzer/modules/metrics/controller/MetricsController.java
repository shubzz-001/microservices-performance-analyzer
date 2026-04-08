package com.analyzer.modules.metrics.controller;

import com.analyzer.modules.metrics.dto.MetricsResponse;
import com.analyzer.modules.metrics.service.MetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping
    public MetricsResponse getMetrics() {
        return metricsService.calculateMetrics();
    }
}
