package com.immortalman01.randomevents.config;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardPBALLTKConfig extends Configuration {

    public ScoreboardPBALLTKConfig(JavaPlugin plugin) {
        super(plugin, "scoreboard_pballtk.yml");
    }

    public List<String> getLines() {
        return getStringList("lines");
    }

    public static String color(String s) {
        if (s == null) return null;
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
