package com.example;

import com.example.service.AppExtractorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppExtractorApplicationTests {

    @Autowired
    private AppExtractorService appExtractorService;

    @Test
    void contextLoads() {
    }

    @Test
    void testExtractEnvironmentVersions() {
        List<String> appList = Arrays.asList(
            "app_aim_ecs_gcg_dev_158298",
            "app_aim_ecs_gcg_prod_158298",
            "app_aim_ecs_gcg_uat_169284",
            "app_aim_ecs_gcg_uat_168988",
            "app_aim_ecs_gcg_dev_169284",
            "app_aim_ecs_gcg_dev_168988"
        );

        Map<String, Object> result = appExtractorService.extractEnvironmentVersions(appList);

        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Check dev environment
        assertTrue(result.containsKey("dev"));
        assertTrue(result.get("dev") instanceof List);
        List<String> devVersions = (List<String>) result.get("dev");
        assertEquals(3, devVersions.size());
        assertTrue(devVersions.contains("158298"));
        assertTrue(devVersions.contains("169284"));
        assertTrue(devVersions.contains("168988"));
        
        // Check prod environment
        assertTrue(result.containsKey("prod"));
        assertEquals("158298", result.get("prod"));
        
        // Check uat environment
        assertTrue(result.containsKey("uat"));
        assertTrue(result.get("uat") instanceof List);
        List<String> uatVersions = (List<String>) result.get("uat");
        assertEquals(2, uatVersions.size());
        assertTrue(uatVersions.contains("169284"));
        assertTrue(uatVersions.contains("168988"));
    }
} 