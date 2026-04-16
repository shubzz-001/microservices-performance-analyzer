package com.analyzer.modules.simulation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analyzer.modules.simulation.service.SimulationService;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {
    
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/run")
    public List<?> runSimulation(@RequestParam(defaultValue = "5") int services) {
        return simulationService.runSimulation(services);
    }

}
