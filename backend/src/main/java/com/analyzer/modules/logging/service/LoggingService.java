package com.analyzer.modules.logging.service;

import com.analyzer.modules.logging.model.LogEntry;
import com.analyzer.modules.logging.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggingService {

    private final LogEntryRepository logEntryRepo;

    public LoggingService(LogEntryRepository logEntryRepository) {
        this.logEntryRepo = logEntryRepository;
    }

    public void log(String serviceName, int latency, boolean success) {
        LogEntry log = new LogEntry(
                null,
                serviceName,
                latency,
                success,
                LocalDateTime.now()
        );

        logEntryRepo.save(log);
    }

}
