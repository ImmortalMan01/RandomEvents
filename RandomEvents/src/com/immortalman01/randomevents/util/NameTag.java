package com.immortalman01.randomevents.util;

import org.bukkit.entity.Player;
import java.util.List;
import java.util.Collections;

public interface NameTag {
    static SimpleNameTag of(String text){
        return new SimpleNameTag(text);
    }
    String getText();
    default void applyTo(Player player, Player pla){}
    default void applyTo(Player player, List<Player> players){}
    static Object getPlayerInfoData(Player player){ return null; }
    static List<Object> getPlayerInfoDataList(Player player){ return Collections.emptyList(); }
}
