package com.adri1711.util.enums;

import org.bukkit.Sound;

public enum XSound {
    ENTITY_BAT_HURT(Sound.ENTITY_BAT_HURT),
    ENTITY_ENDER_DRAGON_DEATH(Sound.ENTITY_ENDER_DRAGON_DEATH),
    ENTITY_PLAYER_LEVELUP(Sound.ENTITY_PLAYER_LEVELUP),
    ENTITY_VILLAGER_DEATH(Sound.ENTITY_VILLAGER_DEATH);

    private final Sound sound;
    XSound(Sound s){ this.sound = s; }
    public Sound parseSound(){ return sound; }
}
