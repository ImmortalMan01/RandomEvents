package com.immortalman01.util;

import java.util.List;
import org.bukkit.entity.Player;

public class FastBoard {
    private final Player player;
    public FastBoard(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    }
    public void updateTitle(String title){}
    public void updateLines(List<String> lines){}
    public void delete(){}
}
