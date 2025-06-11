package com.adri1711.randomevents.util;

import java.lang.reflect.Method;

import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryUtils {

    /**
     * Obtain the title of an inventory from an InventoryClickEvent using
     * reflection to remain compatible across Minecraft versions.
     * 
     * @param event InventoryClickEvent
     * @return title of the inventory or null if not available
     */
    public static String getInventoryTitle(InventoryClickEvent event) {
        if (event == null) {
            return null;
        }
        // First try InventoryView#getTitle()
        try {
            Method viewMethod = event.getClass().getMethod("getView");
            Object view = viewMethod.invoke(event);
            try {
                Method getTitle = view.getClass().getMethod("getTitle");
                Object result = getTitle.invoke(view);
                if (result != null) {
                    return result.toString();
                }
            } catch (NoSuchMethodException ignored) {
                // Older versions may have title() or none
                try {
                    Method title = view.getClass().getMethod("title");
                    Object result = title.invoke(view);
                    if (result != null) {
                        return result.toString();
                    }
                } catch (NoSuchMethodException ex) {
                    // ignore
                }
            }
        } catch (Exception ignore) {
        }
        // Fallback to Inventory#getTitle() or getName()
        try {
            Method getInventory = event.getClass().getMethod("getInventory");
            Object inventory = getInventory.invoke(event);
            try {
                Method getTitle = inventory.getClass().getMethod("getTitle");
                Object result = getTitle.invoke(inventory);
                if (result != null) {
                    return result.toString();
                }
            } catch (NoSuchMethodException ignored) {
                try {
                    Method getName = inventory.getClass().getMethod("getName");
                    Object result = getName.invoke(inventory);
                    if (result != null) {
                        return result.toString();
                    }
                } catch (NoSuchMethodException ex) {
                    // ignore
                }
            }
        } catch (Exception ignore) {
        }
        return null;
    }
}
