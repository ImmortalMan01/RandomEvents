package com.nametagedit.plugin.api;

import org.bukkit.entity.Player;

public class NametagAPI {
    public NametagAPI(Object handler, Object manager) {}
    public void setPrefix(Player p, String prefix){}
    public Nametag getNametag(Player p){ return new Nametag(""); }
    public static class Nametag{
        private final String prefix;
        public Nametag(String prefix){ this.prefix = prefix; }
        public String getPrefix(){ return prefix; }
    }
}
