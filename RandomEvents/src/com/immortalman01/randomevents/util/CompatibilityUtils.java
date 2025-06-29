package com.immortalman01.randomevents.util;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffectType;

public class CompatibilityUtils {
    /**
     * Determine the correct boat entity type for the current server version.
     * <p>
     * Spigot 1.20 exposes the generic {@code EntityType.BOAT} constant which was
     * removed in 1.21 in favour of material-specific boat types. This method
     * attempts to use the generic constant when available and falls back to
     * {@code OAK_BOAT} for newer versions.
     */
    public static EntityType getBoatEntity() {
        try {
            return EntityType.valueOf("BOAT");
        } catch (IllegalArgumentException ex) {
            // 1.21 and newer no longer provide the BOAT constant
            return EntityType.valueOf("OAK_BOAT");
        }
    }

    /**
     * Resolve an EntityType that may have been renamed between versions.
     * @param modern the name in 1.21+
     * @param legacy the name in 1.20
     * @return the EntityType constant if available
     */
    public static EntityType getEntityType(String modern, String legacy) {
        try {
            return EntityType.valueOf(modern);
        } catch (IllegalArgumentException ex) {
            return EntityType.valueOf(legacy);
        }
    }

    /**
     * Resolve a potion effect type that was renamed in 1.21.
     * @param modernName the enum constant used in 1.21 and newer
     * @param legacyName the enum constant used in 1.20 and older
     * @return the resolved PotionEffectType
     */
    public static PotionEffectType getPotionEffect(String modernName, String legacyName) {
        PotionEffectType type = PotionEffectType.getByName(modernName);
        if (type == null) {
            type = PotionEffectType.getByName(legacyName);
        }
        return type;
    }

    /**
     * Obtain the additional tooltip flag if present (1.21+).
     * Returns {@code null} when running on older versions.
     */
    public static ItemFlag getHideAdditionalTooltipFlag() {
        try {
            return ItemFlag.valueOf("HIDE_ADDITIONAL_TOOLTIP");
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
