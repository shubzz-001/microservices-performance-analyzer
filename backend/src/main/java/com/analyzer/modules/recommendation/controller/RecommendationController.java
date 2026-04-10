package com.analyzer.modules.recommendation.controller;

import com.analyzer.modules.ai.service.AIService;
import com.analyzer.modules.recommendation.dto.RecommendationResponse;
import com.analyzer.modules.recommendation.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final AIService aiService;

    public RecommendationController(RecommendationService recommendationService, AIService aiService) {
        this.recommendationService = recommendationService;
        this.aiService = aiService;
    }

    @GetMapping
    public RecommendationResponse getRecommendations() {
        int anomalyCount = aiService.getAnomalyCount();
        return recommendationService.getRecommendations(anomalyCount);
    }

}
