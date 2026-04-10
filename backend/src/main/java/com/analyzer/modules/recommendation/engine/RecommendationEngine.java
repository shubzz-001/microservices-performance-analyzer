package com.analyzer.modules.recommendation.engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendationEngine {

    public List<String> generate(double avgLatency, double failureRate, int anomalyCount) {
        List<String> recommendations = new ArrayList<>();

        if (avgLatency > 200) {
            recommendations.add("High Latency detected -> Consider scaling services or optimizing DB queries");
        }

        if (failureRate > 0.2) {
            recommendations.add("High failure rate -> check service health, implement retries or circuit breakers");
        }

        if (anomalyCount > 2) {
            recommendations.add("Frequent anomalies detected → Possible system bottleneck or unstable service.");
        }

        if (recommendations.isEmpty()) {
            recommendations.add("System operating normally.");
        }

        return recommendations;
    }

}
