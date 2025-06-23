package com.immortalman01.randomevents.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Marker holder used for RandomEvents GUIs so we can reliably detect
 * plugin inventories regardless of title conversion across versions.
 */
public class RandomEventsHolder implements InventoryHolder {

    /** Type of GUI this holder represents. */
    public enum GuiType { STATS, CREDITS, KITS, TEAMS, KILLCOINS }

    private final GuiType type;

    public RandomEventsHolder(GuiType type) {
        this.type = type;
    }

    public GuiType getType() {
        return type;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
