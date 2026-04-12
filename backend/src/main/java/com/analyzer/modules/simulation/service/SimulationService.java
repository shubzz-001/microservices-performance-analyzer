package com.analyzer.modules.simulation.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.analyzer.modules.simulation.events.SimulationEvent;
import com.analyzer.modules.simulation.model.ServiceInstance;
import com.analyzer.modules.simulation.repository.ServiceInstanceRepository;

import java.util.ArrayList;
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

    public List<ServiceInstance> runSimulation() {
        List<ServiceInstance> results = new ArrayList<>();
        Random random = new Random();

        String[] services = {"AuthService", "PaymentService", "OrderService", "InventoryService",};

        for (String service : services) {
            int latency = 50 + random.nextInt(300);
            boolean success = random.nextDouble() > 0.1; // 90% success rate

            ServiceInstance instance = new ServiceInstance(null, service, latency, success);
            results.add(instance);

            publisher.publishEvent(new SimulationEvent(service, latency, success));

            results.add(instance);
        }

        return results;
    } 

}
