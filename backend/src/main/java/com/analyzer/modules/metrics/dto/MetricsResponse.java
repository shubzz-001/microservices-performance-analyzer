package com.analyzer.modules.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricsResponse {
    
    private double averageLatency;
    private double failureRate;
}
