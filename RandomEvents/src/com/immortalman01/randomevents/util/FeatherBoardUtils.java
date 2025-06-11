package com.immortalman01.randomevents.util;

import org.bukkit.entity.Player;

import com.immortalman01.randomevents.RandomEvents;

public class FeatherBoardUtils {

	public static void recoverFeatherBoard(RandomEvents plugin, Player p) {
		if (plugin.getServer().getPluginManager().getPlugin("FeatherBoard") != null) {
			be.maximvdw.featherboard.api.FeatherBoardAPI.toggle(p);
			be.maximvdw.featherboard.api.FeatherBoardAPI.toggle(p);
			
		}
	}

}
