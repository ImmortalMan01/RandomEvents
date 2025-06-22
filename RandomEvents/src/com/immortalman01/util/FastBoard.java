package com.immortalman01.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;
import org.bukkit.entity.Player;

public class FastBoard {
    private final Player player;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Map<Integer, Team> teams = new HashMap<>();

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
        // Remove unused teams if the scoreboard size decreased
        for (Iterator<Map.Entry<Integer, Team>> it = teams.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Team> e = it.next();
            if (e.getKey() >= lines.size()) {
                Team t = e.getValue();
                for (String entry : t.getEntries()) {
                    scoreboard.resetScores(entry);
                }
                t.unregister();
                it.remove();
            }
        }

        int scoreValue = lines.size();
        for (int i = 0; i < lines.size(); i++) {
            String rawLine = lines.get(i);
            String colored = ChatColor.translateAlternateColorCodes('&', rawLine);

            // Limit the line length to the maximum the scoreboard can handle
            if (colored.length() > 32) {
                colored = colored.substring(0, 32);
            }

            String entry = ChatColor.values()[Math.min(i, ChatColor.values().length - 1)].toString();
            Team team = teams.computeIfAbsent(i, key -> {
                Team t = scoreboard.getTeam("fb" + key);
                if (t == null) {
                    t = scoreboard.registerNewTeam("fb" + key);
                }
                t.addEntry(entry);
                return t;
            });

            // Split the line in prefix/suffix while keeping color codes intact
            String prefix;
            String suffix = "";
            if (colored.length() <= 16) {
                prefix = colored;
            } else {
                int prefixEnd = 16;
                if (colored.charAt(prefixEnd - 1) == ChatColor.COLOR_CHAR) {
                    prefixEnd--; // avoid cutting the color character in half
                }
                prefix = colored.substring(0, prefixEnd);

                String remaining = colored.substring(prefixEnd);
                String colorContinuation = ChatColor.getLastColors(prefix);
                suffix = colorContinuation + remaining;
                if (suffix.length() > 16) {
                    suffix = suffix.substring(0, 16);
                    if (suffix.endsWith(String.valueOf(ChatColor.COLOR_CHAR))) {
                        suffix = suffix.substring(0, suffix.length() - 1);
                    }
                }
            }

            team.setPrefix(prefix);
            team.setSuffix(suffix);

            Score score = objective.getScore(entry);
            score.setScore(scoreValue--);
        }
    }

    public void delete() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
