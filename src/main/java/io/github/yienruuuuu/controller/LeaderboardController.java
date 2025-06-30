package io.github.yienruuuuu.controller;

import io.github.yienruuuuu.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Eric.Lee
 * Date: 2025/6/30
 */
@RestController("/api/leaderboard")
public class LeaderboardController {


    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @PostMapping("/add")
    public void addScore(@RequestParam String user, @RequestParam double score) {
        leaderboardService.addScore(user, score);
    }

    @PostMapping("/incr")
    public Double incrementScore(@RequestParam String user, @RequestParam double score) {
        return leaderboardService.incrementScore(user, score);
    }

    @GetMapping("/top")
    public Set<String> getTopUsers(@RequestParam(defaultValue = "5") int limit) {
        return leaderboardService.getTopUsers(limit);
    }

    @GetMapping("/rank")
    public Long getRank(@RequestParam String user) {
        return leaderboardService.getRank(user);
    }

    @GetMapping("/score")
    public Double getScore(@RequestParam String user) {
        return leaderboardService.getScore(user);
    }

}
