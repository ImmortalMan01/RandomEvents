package com.adri1711.util.enums;

public enum Particle1711 {
    DEFAULT,
    REDSTONE;

    public static Particle1711 safeValueOf(String name) {
        if (name == null) {
            return DEFAULT;
        }
        try {
            return Particle1711.valueOf(name.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return DEFAULT;
        }
    }
}
