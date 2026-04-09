package com.analyzer.modules.logging.service;

import com.analyzer.modules.simulation.events.SimulationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SimulationEventListener {

    private final LoggingService loggingService;

    public SimulationEventListener(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @EventListener
    public void handleSimulationEvent(SimulationEvent event) {
        loggingService.log(
                event.getServiceName(),
                event.getLatency(),
                event.isSuccess()
        );
    }

}
