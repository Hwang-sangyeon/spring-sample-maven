package com.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("message", "Spring Boot Maven 샘플 애플리케이션이 정상 동작 중입니다. date:20260813 version:2.0");
        result.put("buildTool", "Maven");
        result.put("timestamp", LocalDateTime.now().toString());
        return result;
    }

    @GetMapping("/hello")
    public Map<String, Object> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("greeting", "Hello, " + name + "!");
        result.put("buildTool", "Maven");
        return result;
    }

}
