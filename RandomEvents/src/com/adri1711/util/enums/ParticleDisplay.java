package com.adri1711.util.enums;

import org.bukkit.Location;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class ParticleDisplay {
    private final Location location;
    private final Effect effect;

    private ParticleDisplay(Location loc, Effect effect) {
        this.location = loc;
        this.effect = effect;
    }

    public static ParticleDisplay display(Object api, Location loc, Particle1711 particle) {
        Effect e = Effect.SMOKE;
        if (particle == Particle1711.REDSTONE) {
            try {
                e = Effect.valueOf("REDSTONE");
            } catch (IllegalArgumentException ignore) {}
        }
        return new ParticleDisplay(loc, e);
    }

    public void display(Player player) {
        if (player != null) {
            player.getWorld().playEffect(location, effect, 0);
        }
    }

    public void spawn(Location loc) {
        if (loc != null && loc.getWorld() != null) {
            loc.getWorld().playEffect(loc, effect, 0);
        }
    }

    public Location getLocation() {
        return location;
    }

    public Effect getEffect() {
        return effect;
    }
}
