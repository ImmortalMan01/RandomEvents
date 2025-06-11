package com.immortalman01.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Score;
import org.bukkit.entity.Player;

public class FastBoard {
    private final Player player;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public FastBoard(Player player) {
        this.player = player;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        Objective obj = null;
        try {
            java.lang.reflect.Method m = scoreboard.getClass().getMethod("registerNewObjective", String.class, String.class, String.class);
            obj = (Objective) m.invoke(scoreboard, "fb", "dummy", "");
        } catch (Throwable ex) {
            try {
                obj = scoreboard.registerNewObjective("fb", "dummy");
            } catch (Throwable ignored) {}
        }
        this.objective = obj;
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    public Player getPlayer() {
        return player;
    }

    public void updateTitle(String title) {
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
    }

    public void updateLines(List<String> lines) {
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }
        int scoreValue = lines.size();
        for (String line : lines) {
            String colored = ChatColor.translateAlternateColorCodes('&', line);
            Score score = objective.getScore(colored);
            score.setScore(scoreValue--);
        }
    }

    public void delete() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
