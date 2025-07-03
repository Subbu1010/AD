package com.example.controller;

import com.example.service.AppExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AppExtractorController {

    @Autowired
    private AppExtractorService appExtractorService;

    @PostMapping("/extract")
    public ResponseEntity<Map<String, Object>> extractEnvironmentVersions(@RequestBody List<String> appList) {
        try {
            Map<String, Object> result = appExtractorService.extractEnvironmentVersions(appList);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is running!");
    }
} 