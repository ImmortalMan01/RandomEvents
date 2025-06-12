package com.immortalman01.randomevents.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Marker holder used for RandomEvents GUIs so we can reliably detect
 * plugin inventories regardless of title conversion across versions.
 */
public class RandomEventsHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}
