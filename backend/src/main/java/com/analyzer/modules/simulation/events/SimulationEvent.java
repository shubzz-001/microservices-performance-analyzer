package com.analyzer.modules.simulation.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationEvent {

    private String serviceName;
    private int latency;
    private boolean success;

}
