package org.spigotmc.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityMountEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public EntityMountEvent(Entity mount, Entity entity) {}
    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
