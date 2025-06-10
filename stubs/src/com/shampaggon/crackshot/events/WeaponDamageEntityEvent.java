package com.shampaggon.crackshot.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WeaponDamageEntityEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public Player getPlayer() { return null; }
    public LivingEntity getVictim() { return null; }
    public Entity getDamager(){ return null; }
    public double getDamage() { return 0; }
    public void setDamage(double d) {}
    public void setCancelled(boolean cancel) {}
    public boolean isCancelled() { return false; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
