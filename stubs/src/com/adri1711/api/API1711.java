package com.adri1711.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.adri1711.util.FastBoard;

public class API1711 {
    public API1711(String user, String plugin){}
    public void usaTitle(Player p, String title, String message){}
    public void createWorldBorder(Player p, Double size, Location center){}
    public String toJson(Object obj){ return ""; }
    public Object fromJson(java.io.Reader reader, Class<?> cls){ return null; }
    public FastBoard createFastBoard(Player p){ return new FastBoard(p); }
}
