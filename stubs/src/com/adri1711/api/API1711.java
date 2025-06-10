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
    public boolean isCraftBoat(Object e){ return false; }
    public boolean isCraftEnderPearl(Object e){ return false; }
    public boolean isCraftHorse(Object e){ return false; }
    public boolean isCraftItem(Object e){ return false; }
    public void convertBlock(Location loc, Object data){}
    public double getHorseSpeed(Object horse){ return 0.0; }
    public void setHorseSpeed(Object horse, double speed){}
    public void correctDirectionFireball(Object fireball, Object v){}
    public void send(Player p, String a, String b, java.util.List<String> c, String d, String e){}
}
