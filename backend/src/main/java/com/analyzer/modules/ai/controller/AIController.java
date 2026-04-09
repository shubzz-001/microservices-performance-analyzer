package com.analyzer.modules.ai.controller;

import com.analyzer.modules.ai.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/analyze")
    public String analyze() {
        return aiService.analyzeLogs();
    }

}
