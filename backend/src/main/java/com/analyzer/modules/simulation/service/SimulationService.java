package com.analyzer.modules.simulation.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.analyzer.modules.simulation.model.ServiceInstance;
import com.analyzer.modules.simulation.repository.ServiceInstanceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SimulationService {

    private final ServiceInstanceRepository repository;
    private final ApplicationEventPublisher publisher;
    
    public SimulationService(ServiceInstanceRepository repository,  ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public List<ServiceInstance> runSimulation(int count) {
        List<ServiceInstance> results = new ArrayList<>();
        Random random = new Random();

        String[] services = {"AuthService", "PaymentService", "OrderService", "InventoryService",};

        for (int i = 1; i <= count; i++) {
            int latency = 50 + random.nextInt(300);
            boolean success = random.nextDouble() > 0.1;

            results.add(new ServiceInstance(
                    null,
                    "Service-"+i,
                    latency,
                    success,
                    new Date(System.currentTimeMillis()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
            ));
        }

        return results;
    } 

}
