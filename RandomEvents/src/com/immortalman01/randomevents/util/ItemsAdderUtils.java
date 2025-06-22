package com.immortalman01.randomevents.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

/**
 * Helper methods to interact with ItemsAdder using reflection.
 * This keeps RandomEvents free of a hard dependency on the API.
 */
public final class ItemsAdderUtils {
    private static Boolean available;

    private ItemsAdderUtils() {}

    private static boolean checkAvailable() {
        if (available == null) {
            available = Bukkit.getPluginManager().getPlugin("ItemsAdder") != null;
        }
        return available;
    }

    public static boolean isAvailable() {
        return checkAvailable();
    }

    /**
     * Obtain the ItemsAdder namespaced ID of the given item.
     * Returns null if the item is not an ItemsAdder custom item or if
     * the API is not present.
     */
    public static String getNamespacedId(ItemStack item) {
        if (item == null || !checkAvailable()) {
            return null;
        }
        try {
            Class<?> cs = Class.forName("dev.lone.itemsadder.api.CustomStack");
            Method byItem = cs.getMethod("byItemStack", ItemStack.class);
            Object customStack = byItem.invoke(null, item);
            if (customStack != null) {
                Method idMethod = customStack.getClass().getMethod("getNamespacedID");
                Object id = idMethod.invoke(customStack);
                if (id instanceof String) {
                    return (String) id;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Create an ItemsAdder item by its namespaced ID.
     * Returns null if the ID is invalid or the API is not present.
     */
    public static ItemStack createItem(String id) {
        if (id == null || !checkAvailable()) {
            return null;
        }
        try {
            Class<?> cs = Class.forName("dev.lone.itemsadder.api.CustomStack");
            Method get = cs.getMethod("getInstance", String.class);
            Object customStack = get.invoke(null, id);
            if (customStack != null) {
                Method itemMethod = customStack.getClass().getMethod("getItemStack");
                Object item = itemMethod.invoke(customStack);
                if (item instanceof ItemStack) {
                    return ((ItemStack) item).clone();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Create an ItemsAdder item by its ID and copy meta from the original stack.
     * This helps preserve enchantments and other NBT data when restoring
     * inventories.
     */
    public static ItemStack createItem(String id, ItemStack original) {
        ItemStack item = createItem(id);
        if (item != null && original != null) {
            try {
                if (original.hasItemMeta()) {
                    item.setItemMeta(original.getItemMeta().clone());
                }
                item.setAmount(original.getAmount());
            } catch (Exception ignored) {
            }
        }
        return item;
    }
}
