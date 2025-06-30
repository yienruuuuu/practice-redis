package io.github.yienruuuuu.controller;

import io.github.yienruuuuu.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/set")
    public ResponseEntity<Void> set(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestParam String key) {
        return ResponseEntity.ok(redisService.getValue(key));
    }
}
