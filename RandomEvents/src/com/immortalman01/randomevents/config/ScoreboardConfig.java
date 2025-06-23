package com.immortalman01.randomevents.config;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardConfig extends Configuration {

    public ScoreboardConfig(JavaPlugin plugin) {
        super(plugin, plugin.getConfig().getString("language", "en").equalsIgnoreCase("tr")
                ? "scoreboard_tr.yml" : "scoreboard.yml");
    }

    public List<String> getLayout(String key) {
        return getStringList("events." + key);
    }

    public String getTitle(String key) {
        String path = "titles." + key;
        if (isString(path)) {
            return color(getString(path));
        }
        String def = getString("titles.DEFAULT");
        return color(def);
    }

    public List<String> getLines(String key) {
        return getStringList("events." + key);
    }

    public static String color(String s) {
        if (s == null) return null;
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
