package com.shampaggon.crackshot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WeaponExplodeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public Player getPlayer() { return null; }
    public void setCancelled(boolean cancel) {}
    public boolean isCancelled() { return false; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
