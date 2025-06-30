package io.github.yienruuuuu.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@Service("leaderboardService")
public class LeaderboardService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String LEADERBOARD_KEY = "leaderboard";

    public LeaderboardService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 新增或更新分數（ZADD）
    public void addScore(String user, double score) {
        redisTemplate.opsForZSet().add(LEADERBOARD_KEY, user, score);
    }

    // 累加分數（ZINCRBY）
    public Double incrementScore(String user, double score) {
        return redisTemplate.opsForZSet().incrementScore(LEADERBOARD_KEY, user, score);
    }

    // 取得前 N 名（ZREVRANGE）
    public Set<String> getTopUsers(int topN) {
        return redisTemplate.opsForZSet().reverseRange(LEADERBOARD_KEY, 0, topN - 1);
    }

    // 查某使用者排名（ZREVRANK）
    public Long getRank(String user) {
        return redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, user);
    }

    // 查某使用者分數（ZSCORE）
    public Double getScore(String user) {
        return redisTemplate.opsForZSet().score(LEADERBOARD_KEY, user);
    }
}
