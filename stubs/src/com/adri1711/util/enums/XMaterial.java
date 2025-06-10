package com.adri1711.util.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class XMaterial {
    private final Material mat;
    private XMaterial(Material mat){ this.mat = mat; }
    public Material parseMaterial(){ return mat; }
    public ItemStack parseItem(){ return mat == null ? null : new ItemStack(mat); }
    public static XMaterial valueOf(String name){ try { return new XMaterial(Material.valueOf(name)); } catch(Exception e){ return new XMaterial(null); } }
    public static final XMaterial AIR = valueOf("AIR");
    public static final XMaterial ANVIL = valueOf("ANVIL");
    public static final XMaterial ARROW = valueOf("ARROW");
    public static final XMaterial BEDROCK = valueOf("BEDROCK");
    public static final XMaterial BEEF = valueOf("BEEF");
    public static final XMaterial BLACK_TERRACOTTA = valueOf("BLACK_TERRACOTTA");
    public static final XMaterial BLAZE_ROD = valueOf("BLAZE_ROD");
    public static final XMaterial BLUE_TERRACOTTA = valueOf("BLUE_TERRACOTTA");
    public static final XMaterial BOW = valueOf("BOW");
    public static final XMaterial BROWN_TERRACOTTA = valueOf("BROWN_TERRACOTTA");
    public static final XMaterial CHEST = valueOf("CHEST");
    public static final XMaterial CHIPPED_ANVIL = valueOf("CHIPPED_ANVIL");
    public static final XMaterial COAL_BLOCK = valueOf("COAL_BLOCK");
    public static final XMaterial COOKED_BEEF = valueOf("COOKED_BEEF");
    public static final XMaterial COOKED_PORKCHOP = valueOf("COOKED_PORKCHOP");
    public static final XMaterial DAMAGED_ANVIL = valueOf("DAMAGED_ANVIL");
    public static final XMaterial DIAMOND_HOE = valueOf("DIAMOND_HOE");
    public static final XMaterial DIAMOND_SHOVEL = valueOf("DIAMOND_SHOVEL");
    public static final XMaterial DIRT = valueOf("DIRT");
    public static final XMaterial EGG = valueOf("EGG");
    public static final XMaterial EMERALD = valueOf("EMERALD");
    public static final XMaterial EMERALD_BLOCK = valueOf("EMERALD_BLOCK");
    public static final XMaterial ENDER_EYE = valueOf("ENDER_EYE");
    public static final XMaterial GLASS = valueOf("GLASS");
    public static final XMaterial GOLDEN_HELMET = valueOf("GOLDEN_HELMET");
    public static final XMaterial GOLDEN_HOE = valueOf("GOLDEN_HOE");
    public static final XMaterial GOLDEN_SHOVEL = valueOf("GOLDEN_SHOVEL");
    public static final XMaterial GOLDEN_SWORD = valueOf("GOLDEN_SWORD");
    public static final XMaterial GRAY_TERRACOTTA = valueOf("GRAY_TERRACOTTA");
    public static final XMaterial GREEN_TERRACOTTA = valueOf("GREEN_TERRACOTTA");
    public static final XMaterial IRON_BOOTS = valueOf("IRON_BOOTS");
    public static final XMaterial IRON_HOE = valueOf("IRON_HOE");
    public static final XMaterial IRON_HORSE_ARMOR = valueOf("IRON_HORSE_ARMOR");
    public static final XMaterial IRON_SHOVEL = valueOf("IRON_SHOVEL");
    public static final XMaterial IRON_SWORD = valueOf("IRON_SWORD");
    public static final XMaterial LEATHER_CHESTPLATE = valueOf("LEATHER_CHESTPLATE");
    public static final XMaterial MAP = valueOf("MAP");
    public static final XMaterial NETHER_STAR = valueOf("NETHER_STAR");
    public static final XMaterial OAK_BOAT = valueOf("OAK_BOAT");
    public static final XMaterial OAK_SIGN = valueOf("OAK_SIGN");
    public static final XMaterial ORANGE_DYE = valueOf("ORANGE_DYE");
    public static final XMaterial PURPLE_TERRACOTTA = valueOf("PURPLE_TERRACOTTA");
    public static final XMaterial REDSTONE_BLOCK = valueOf("REDSTONE_BLOCK");
    public static final XMaterial RED_DYE = valueOf("RED_DYE");
    public static final XMaterial RED_TERRACOTTA = valueOf("RED_TERRACOTTA");
    public static final XMaterial SADDLE = valueOf("SADDLE");
    public static final XMaterial SKELETON_SKULL = valueOf("SKELETON_SKULL");
    public static final XMaterial SNOW = valueOf("SNOW");
    public static final XMaterial SNOWBALL = valueOf("SNOWBALL");
    public static final XMaterial STONE = valueOf("STONE");
    public static final XMaterial STONE_HOE = valueOf("STONE_HOE");
    public static final XMaterial STONE_PRESSURE_PLATE = valueOf("STONE_PRESSURE_PLATE");
    public static final XMaterial STONE_SHOVEL = valueOf("STONE_SHOVEL");
    public static final XMaterial STONE_SWORD = valueOf("STONE_SWORD");
    public static final XMaterial TNT = valueOf("TNT");
    public static final XMaterial TOTEM_OF_UNDYING = valueOf("TOTEM_OF_UNDYING");
    public static final XMaterial TROPICAL_FISH = valueOf("TROPICAL_FISH");
    public static final XMaterial WATER = valueOf("WATER");
    public static final XMaterial WATER_BUCKET = valueOf("WATER_BUCKET");
    public static final XMaterial WHITE_STAINED_GLASS = valueOf("WHITE_STAINED_GLASS");
    public static final XMaterial WHITE_STAINED_GLASS_PANE = valueOf("WHITE_STAINED_GLASS_PANE");
    public static final XMaterial WHITE_TERRACOTTA = valueOf("WHITE_TERRACOTTA");
    public static final XMaterial WOODEN_HOE = valueOf("WOODEN_HOE");
    public static final XMaterial WOODEN_SHOVEL = valueOf("WOODEN_SHOVEL");
    public static final XMaterial WOODEN_SWORD = valueOf("WOODEN_SWORD");
    public static final XMaterial YELLOW_TERRACOTTA = valueOf("YELLOW_TERRACOTTA");
    public static final XMaterial BLACK_WOOL = valueOf("BLACK_WOOL");
    public static final XMaterial BLUE_WOOL = valueOf("BLUE_WOOL");
    public static final XMaterial BROWN_WOOL = valueOf("BROWN_WOOL");
    public static final XMaterial GRAY_WOOL = valueOf("GRAY_WOOL");
    public static final XMaterial GREEN_WOOL = valueOf("GREEN_WOOL");
    public static final XMaterial PURPLE_WOOL = valueOf("PURPLE_WOOL");
    public static final XMaterial RED_WOOL = valueOf("RED_WOOL");
    public static final XMaterial YELLOW_WOOL = valueOf("YELLOW_WOOL");
}
