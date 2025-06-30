package io.github.yienruuuuu.controller;

import io.github.yienruuuuu.service.QueueService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@RestController
@RequestMapping("api/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/enqueue")
    public void enqueue(@RequestParam String task) {
        queueService.enqueue(task);
    }

    @PostMapping("/dequeue")
    public String dequeue() {
        return queueService.dequeue();
    }

    @GetMapping("/peek")
    public String peek() {
        return queueService.peek();
    }

    @GetMapping("/size")
    public long size() {
        return queueService.size();
    }
}
