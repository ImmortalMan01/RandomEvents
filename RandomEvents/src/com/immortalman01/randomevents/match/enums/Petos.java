package com.immortalman01.randomevents.match.enums;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.immortalman01.util.enums.XMaterial;

import net.md_5.bungee.api.ChatColor;

public enum Petos {

	RED(0, Color.RED, DyeColor.RED, XMaterial.RED_WOOL, XMaterial.RED_TERRACOTTA, ChatColor.RED, "Red"),

	BLUE(1, Color.BLUE, DyeColor.BLUE, XMaterial.BLUE_WOOL, XMaterial.BLUE_TERRACOTTA, ChatColor.BLUE, "Blue"),

	GREEN(2, Color.GREEN, DyeColor.GREEN, XMaterial.GREEN_WOOL, XMaterial.GREEN_TERRACOTTA, ChatColor.GREEN, "Green"),

	YELLOW(3, Color.YELLOW, DyeColor.YELLOW, XMaterial.YELLOW_WOOL, XMaterial.YELLOW_TERRACOTTA, ChatColor.YELLOW,
			"Yellow"),

	GRAY(4, Color.GRAY, DyeColor.GRAY, XMaterial.GRAY_WOOL, XMaterial.GRAY_TERRACOTTA, ChatColor.GRAY, "Gray"),

	PURPLE(5, Color.PURPLE, DyeColor.PURPLE, XMaterial.PURPLE_WOOL, XMaterial.PURPLE_TERRACOTTA, ChatColor.LIGHT_PURPLE,
			"Purple"),

	BLACK(6, Color.BLACK, DyeColor.BLACK, XMaterial.BLACK_WOOL, XMaterial.BLACK_TERRACOTTA, ChatColor.BLACK, "Black"),

	BROWN(7, Color.MAROON, DyeColor.BROWN, XMaterial.BROWN_WOOL, XMaterial.BROWN_TERRACOTTA, ChatColor.GOLD, "Brown");

	private Integer team;

	private Color color;
	private DyeColor dye;
	private XMaterial wool;
	private XMaterial clay;
	private ChatColor chatColor;
	private String name;

	private Petos(Integer team, Color color, DyeColor dye, XMaterial wool, XMaterial clay, ChatColor chatColor,
			String name) {
		this.team = team;
		this.color = color;
		this.dye = dye;
		this.wool = wool;
		this.clay = clay;
		this.chatColor = chatColor;
		this.name = name;
	}

	public XMaterial getWool() {
		return wool;
	}

	public void setWool(XMaterial wool) {
		this.wool = wool;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public void setChatColor(ChatColor chatColor) {
		this.chatColor = chatColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}

	public DyeColor getDye() {
		return dye;
	}

	public void setDye(DyeColor dye) {
		this.dye = dye;
	}

	
	public XMaterial getClay() {
		return clay;
	}

	public void setClay(XMaterial clay) {
		this.clay = clay;
	}

	public static Petos getPeto(Integer peto) {
		Petos res = null;
		for (Petos p : Petos.values()) {
			if (p.getTeam().equals(peto)) {
				res = p;
			}
		}
		return res;
	}

	public ItemStack getPeto() {
		ItemStack peto = XMaterial.LEATHER_CHESTPLATE.parseItem();
		LeatherArmorMeta petoMeta = (LeatherArmorMeta) peto.getItemMeta();
		petoMeta.setColor(getColor());
		peto.setItemMeta(petoMeta);
		return peto;
	}

       /**
        * Obtain the wool item for this team colour using modern materials.
        *
        * <p>The legacy {@code setDurability} APIs triggered Bukkit's legacy
        * material support which caused a noticeable lag spike on modern
        * servers. Since terracotta and wool blocks have a dedicated material
        * for each colour in 1.20+, simply creating the item with the correct
        * material is sufficient.</p>
        */
       public ItemStack getWoolItem() {
               return getWool().parseItem();
       }

       /**
        * Obtain the terracotta item for this team colour using modern
        * materials. Avoids legacy data manipulation.
        */
       public ItemStack getClayItem() {
               return getClay().parseItem();
       }

}
