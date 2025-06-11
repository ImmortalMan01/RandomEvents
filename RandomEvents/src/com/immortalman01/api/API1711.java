package com.immortalman01.api;

import java.io.Reader;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import com.immortalman01.util.FastBoard;
import com.google.gson.Gson;

public class API1711 {
    public API1711(String user, String plugin) {}

    public void usaTitle(Player p, String title, String message) {
        if (p != null) {
            try {
                java.lang.reflect.Method m = p.getClass().getMethod("sendTitle", String.class, String.class, int.class, int.class, int.class);
                m.invoke(p, title, message, 10, 70, 20);
            } catch (Throwable ex) {
                try {
                    java.lang.reflect.Method m = p.getClass().getMethod("sendTitle", String.class, String.class);
                    m.invoke(p, title, message);
                } catch (Throwable ignore) {
                }
            }
        }
    }

    public void createWorldBorder(Player p, Double size, Location center) {
        if (p == null || center == null) return;
        try {
            WorldBorder border = p.getWorld().getWorldBorder();
            border.setCenter(center);
            border.setSize(size);
        } catch (Throwable ignore) {
        }
    }

    public String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public Object fromJson(Reader reader, Class<?> cls) {
        return new Gson().fromJson(reader, cls);
    }

    public FastBoard createFastBoard(Player p) {
        return new FastBoard(p);
    }

    public boolean isCraftBoat(Object e) {
        return e instanceof org.bukkit.entity.Boat ||
               e.getClass().getSimpleName().equalsIgnoreCase("CraftBoat");
    }

    public boolean isCraftEnderPearl(Object e) {
        return e instanceof org.bukkit.entity.EnderPearl ||
               e.getClass().getSimpleName().equalsIgnoreCase("CraftEnderPearl");
    }

    public boolean isCraftHorse(Object e) {
        return e instanceof org.bukkit.entity.Horse ||
               e.getClass().getSimpleName().toLowerCase().contains("crafthorse");
    }

    public boolean isCraftItem(Object e) {
        return e instanceof org.bukkit.entity.Item ||
               e.getClass().getSimpleName().equalsIgnoreCase("CraftItem");
    }

    public void convertBlock(Location loc, Object data) {
        if (loc == null || data == null) return;
        try {
            if (data instanceof MaterialData) {
                MaterialData md = (MaterialData) data;
                Block b = loc.getBlock();
                b.setType(md.getItemType());
                try {
                    b.setData(md.getData());
                } catch (Throwable ignore) {}
            }
        } catch (Throwable ignore) {}
    }

    public double getHorseSpeed(Object horse) {
        if (horse instanceof Horse) {
            try {
                Class<?> attrClass = Class.forName("org.bukkit.attribute.Attribute");
                Object type = Enum.valueOf((Class<Enum>) attrClass, "GENERIC_MOVEMENT_SPEED");
                java.lang.reflect.Method getAttr = horse.getClass().getMethod("getAttribute", attrClass);
                Object attr = getAttr.invoke(horse, type);
                java.lang.reflect.Method getBase = attr.getClass().getMethod("getBaseValue");
                return ((Number) getBase.invoke(attr)).doubleValue();
            } catch (Throwable ignore) {
            }
        }
        return 0.0;
    }

    public void setHorseSpeed(Object horse, double speed) {
        if (horse instanceof Horse) {
            try {
                Class<?> attrClass = Class.forName("org.bukkit.attribute.Attribute");
                Object type = Enum.valueOf((Class<Enum>) attrClass, "GENERIC_MOVEMENT_SPEED");
                java.lang.reflect.Method getAttr = horse.getClass().getMethod("getAttribute", attrClass);
                Object attr = getAttr.invoke(horse, type);
                java.lang.reflect.Method setBase = attr.getClass().getMethod("setBaseValue", double.class);
                setBase.invoke(attr, speed);
            } catch (Throwable ignore) {
            }
        }
    }

    public void correctDirectionFireball(Object fireball, Object v) {
        if (fireball instanceof Fireball && v instanceof Vector) {
            Fireball fb = (Fireball) fireball;
            Vector vec = ((Vector) v).clone();
            fb.setDirection(vec);
            fb.setVelocity(vec);
        }
    }

    public void send(Player p, String a, String b, List<String> c, String d, String e) {
        if (p == null) return;
        if (a != null) p.sendMessage(a);
        if (b != null) p.sendMessage(b);
        if (c != null) for (String s : c) p.sendMessage(s);
        if (d != null) p.sendMessage(d);
        if (e != null) p.sendMessage(e);
    }

    public String getInventoryName(InventoryClickEvent e) {
        if (e == null) return "";
        try {
            return e.getView().getTitle();
        } catch (Throwable t) {
            return "";
        }
    }
}
