package org.spigotmc.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityDismountEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public EntityDismountEvent(Entity mount, Entity entity) {}
    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
