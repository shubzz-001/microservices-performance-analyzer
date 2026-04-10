package com.analyzer.modules.recommendation.service;

import com.analyzer.modules.metrics.dto.MetricsResponse;
import com.analyzer.modules.metrics.service.MetricsService;
import com.analyzer.modules.recommendation.dto.RecommendationResponse;
import com.analyzer.modules.recommendation.engine.RecommendationEngine;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final MetricsService metricsService;
    private final RecommendationEngine engine;

    public RecommendationService(MetricsService metricsService, RecommendationEngine engine) {
        this.metricsService = metricsService;
        this.engine = engine;
    }

    public RecommendationResponse getRecommendations(int anomalyCount) {
        MetricsResponse metrics = metricsService.calculateMetrics();

        return new RecommendationResponse(
                engine.generate(
                        metrics.getAverageLatency(),
                        metrics.getFailureRate(),
                        anomalyCount
                )
        );
    }



}
