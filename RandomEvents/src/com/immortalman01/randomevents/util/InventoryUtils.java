package com.immortalman01.randomevents.util;

import java.lang.reflect.Method;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryUtils {

    /**
     * Convert an Adventure Component to a legacy string if possible. This
     * method uses reflection to avoid compile-time dependency on the
     * Adventure API which only exists on modern server versions.
     */
    private static String componentToString(Object component) {
        if (component == null) {
            return null;
        }
        try {
            Class<?> compClass = Class.forName("net.kyori.adventure.text.Component");
            if (compClass.isInstance(component)) {
                Class<?> serializerClass = Class
                        .forName("net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer");
                Method legacySection = serializerClass.getMethod("legacySection");
                Object serializer = legacySection.invoke(null);
                Method serialize = serializer.getClass().getMethod("serialize", compClass);
                Object result = serialize.invoke(serializer, component);
                if (result != null) {
                    return result.toString();
                }
            }
        } catch (Exception ignore) {
            // Ignore and fallback to toString
        }
        return component.toString();
    }

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
                    return componentToString(result);
                }
            } catch (NoSuchMethodException ignored) {
                // Older versions may have title() or none
                try {
                    Method title = view.getClass().getMethod("title");
                    Object result = title.invoke(view);
                    if (result != null) {
                        return componentToString(result);
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
                    return componentToString(result);
                }
            } catch (NoSuchMethodException ignored) {
                try {
                    Method getName = inventory.getClass().getMethod("getName");
                    Object result = getName.invoke(inventory);
                    if (result != null) {
                        return componentToString(result);
                    }
                } catch (NoSuchMethodException ex) {
                    // ignore
                }
            }
        } catch (Exception ignore) {
        }
        return null;
    }

    /**
     * Obtain the title of an inventory from an InventoryDragEvent using
     * reflection to remain compatible across Minecraft versions.
     *
     * @param event InventoryDragEvent
     * @return title of the inventory or null if not available
     */
    public static String getInventoryTitle(InventoryDragEvent event) {
        if (event == null) {
            return null;
        }
        try {
            Method viewMethod = event.getClass().getMethod("getView");
            Object view = viewMethod.invoke(event);
            try {
                Method getTitle = view.getClass().getMethod("getTitle");
                Object result = getTitle.invoke(view);
                if (result != null) {
                    return componentToString(result);
                }
            } catch (NoSuchMethodException ignored) {
                try {
                    Method title = view.getClass().getMethod("title");
                    Object result = title.invoke(view);
                    if (result != null) {
                        return componentToString(result);
                    }
                } catch (NoSuchMethodException ex) {
                    // ignore
                }
            }
        } catch (Exception ignore) {
        }

        try {
            Method getInventory = event.getClass().getMethod("getInventory");
            Object inventory = getInventory.invoke(event);
            try {
                Method getTitle = inventory.getClass().getMethod("getTitle");
                Object result = getTitle.invoke(inventory);
                if (result != null) {
                    return componentToString(result);
                }
            } catch (NoSuchMethodException ignored) {
                try {
                    Method getName = inventory.getClass().getMethod("getName");
                    Object result = getName.invoke(inventory);
                    if (result != null) {
                        return componentToString(result);
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
