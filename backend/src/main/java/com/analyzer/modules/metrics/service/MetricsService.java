package com.analyzer.modules.metrics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analyzer.modules.metrics.dto.MetricsResponse;
import com.analyzer.modules.simulation.model.ServiceInstance;
import com.analyzer.modules.simulation.repository.ServiceInstanceRepository;

@Service
public class MetricsService {
    
    private final ServiceInstanceRepository repository;
    
    public MetricsService(ServiceInstanceRepository repository) {
        this.repository = repository;
    }

    public MetricsResponse calculateMetrics() {
        List<ServiceInstance> data = repository.findAll();

        if (data.isEmpty()) {
            return new MetricsResponse(0, 0);   
        }

        double averageLatency = data.stream()
                                    .mapToInt(ServiceInstance::getLatency)
                                    .average()
                                    .orElse(0);

        long failures = data.stream()
                            .filter(instance -> !instance.isSuccess())
                            .count();

        double failureRate = (double) failures / data.size();

        return new MetricsResponse(averageLatency, failureRate);
    }

}
