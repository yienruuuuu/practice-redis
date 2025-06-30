package io.github.yienruuuuu.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@Service("queueService")
public class QueueService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String TASK_QUEUE_KEY = "task:queue";

    public QueueService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 加入任務（生產者）
    public void enqueue(String task) {
        redisTemplate.opsForList().rightPush(TASK_QUEUE_KEY, task);
    }

    // 取出任務（消費者）
    public String dequeue() {
        return redisTemplate.opsForList().leftPop(TASK_QUEUE_KEY);
    }

    // 查看佇列最前任務
    public String peek() {
        List<String> list = redisTemplate.opsForList().range(TASK_QUEUE_KEY, 0, 0);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    // 查佇列長度
    public long size() {
        return redisTemplate.opsForList().size(TASK_QUEUE_KEY);
    }
}
