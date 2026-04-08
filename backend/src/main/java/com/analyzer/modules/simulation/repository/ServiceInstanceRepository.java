package com.analyzer.modules.simulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analyzer.modules.simulation.model.ServiceInstance;

@Repository
public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance, Long> {
    
}
