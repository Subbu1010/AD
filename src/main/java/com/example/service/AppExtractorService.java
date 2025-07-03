package com.example.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppExtractorService {

    private static final Pattern APP_PATTERN = Pattern.compile("app_aim_ecs_gcg_(\\w+)_(\\d+)");

    public Map<String, Object> extractEnvironmentVersions(List<String> appList) {
        Map<String, Set<String>> environmentVersions = new HashMap<>();
        
        for (String app : appList) {
            Matcher matcher = APP_PATTERN.matcher(app);
            if (matcher.find()) {
                String environment = matcher.group(1);
                String version = matcher.group(2);
                
                environmentVersions.computeIfAbsent(environment, k -> new HashSet<>()).add(version);
            }
        }
        
        // Convert to the required format
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : environmentVersions.entrySet()) {
            Set<String> versions = entry.getValue();
            if (versions.size() == 1) {
                // Single version - return as string
                result.put(entry.getKey(), versions.iterator().next());
            } else {
                // Multiple versions - return as sorted list
                List<String> sortedVersions = new ArrayList<>(versions);
                Collections.sort(sortedVersions);
                result.put(entry.getKey(), sortedVersions);
            }
        }
        
        return result;
    }
} 