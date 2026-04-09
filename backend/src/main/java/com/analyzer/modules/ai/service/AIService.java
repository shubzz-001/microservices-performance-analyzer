package com.analyzer.modules.ai.service;

import com.analyzer.modules.ai.adaptor.AIClient;
import com.analyzer.modules.ai.dto.AnomalyRequest;
import com.analyzer.modules.ai.dto.LogDTO;
import com.analyzer.modules.logging.model.LogEntry;
import com.analyzer.modules.logging.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AIService {

    private final LogEntryRepository LogEntryRepo;
    private final AIClient aiClient;

    public AIService(LogEntryRepository LogEntryRepo, AIClient aiClient) {
        this.LogEntryRepo = LogEntryRepo;
        this.aiClient = aiClient;
    }

    public String analyzeLogs() {
        List<LogEntry> logs = LogEntryRepo.findAll();

        List<LogDTO> logDTOS = logs.stream()
                .map( l -> new LogDTO(l.getLatency(), l.isSuccess()))
                .toList();

        AnomalyRequest request = new AnomalyRequest(logDTOS);

        return aiClient.detectAnomalies(request);
    }

}
