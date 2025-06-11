package com.immortalman01.randomevents.util;

import java.io.BufferedReader;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.match.Kit;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.PlayersDisabled;
import com.immortalman01.randomevents.match.WaterDropStep;
import com.immortalman01.randomevents.match.schedule.Schedule;
import com.immortalman01.randomevents.match.utils.BannedPlayers;
import com.immortalman01.randomevents.match.utils.InventoryPers;
import com.immortalman01.randomevents.json.GsonFactory;
import com.google.gson.Gson;

public class UtilidadesJson {

       private static final Gson GSON = GsonFactory.getPrettyGson();

	public static String fromMatchToJSON(RandomEvents plugin, Match match) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(match);

			// JsonSerializerDeserializer<BossMatch> jsonSerializer = new
			// JsonSerializerDeserializer<BossMatch>();
			//
			// resultado = jsonSerializer.serialize(match);
		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating match");
		}
		return resultado;

	}

	public static Match fromJSONToMatch(RandomEvents plugin, BufferedReader br) {
                Match match = null;
                try {
                        match = GSON.fromJson(br, Match.class);
			UtilsRandomEvents.normalizaColorsMatch(match);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading match: " + br);
		}
		return match;
	}
	
	public static String fromWDToJSON(RandomEvents plugin, WaterDropStep match) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(match);

			// JsonSerializerDeserializer<BossMatch> jsonSerializer = new
			// JsonSerializerDeserializer<BossMatch>();
			//
			// resultado = jsonSerializer.serialize(match);
		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating waterDrop");
		}
		return resultado;

	}

	public static WaterDropStep fromJSONToWD(RandomEvents plugin, BufferedReader br) {
                WaterDropStep match = null;
                try {
                        match = GSON.fromJson(br, WaterDropStep.class);
			UtilsRandomEvents.normalizaColorsWaterDrop(match);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading waterDrop: " + br);
		}
		return match;
	}
	
	public static String fromKitToJSON(RandomEvents plugin, Kit match) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(match);

			// JsonSerializerDeserializer<BossMatch> jsonSerializer = new
			// JsonSerializerDeserializer<BossMatch>();
			//
			// resultado = jsonSerializer.serialize(match);
		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating Kit");
		}
		return resultado;

	}

	public static Kit fromJSONToKit(RandomEvents plugin, BufferedReader br) {
                Kit match = null;
                try {
                        match = GSON.fromJson(br, Kit.class);
			UtilsRandomEvents.normalizaColorsKit(match);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading Kit: " + br);
		}
		return match;
	}
	

	public static String fromScheduleToJSON(RandomEvents plugin, Schedule match) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(match);

			// JsonSerializerDeserializer<BossMatch> jsonSerializer = new
			// JsonSerializerDeserializer<BossMatch>();
			//
			// resultado = jsonSerializer.serialize(match);
		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating schedule");
		}
		return resultado;

	}

	public static Schedule fromJSONToSchedule(RandomEvents plugin, BufferedReader br) {
                Schedule match = null;
                try {
                        match = GSON.fromJson(br, Schedule.class);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading schedule: " + br);
		}
		return match;
	}

	public static String fromInventoryToJSON(RandomEvents plugin, InventoryPers inventory) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(inventory);

			// JsonSerializerDeserializer<BossMatch> jsonSerializer = new
			// JsonSerializerDeserializer<BossMatch>();
			//
			// resultado = jsonSerializer.serialize(match);
		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating inventory");
		}
		return resultado;

	}

	public static InventoryPers fromJSONToInventory(RandomEvents plugin, BufferedReader br) {
                InventoryPers inventory = null;
                try {
                        inventory = GSON.fromJson(br, InventoryPers.class);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading inventory: " + br);
		}
		return inventory;
	}
	
	public static String fromBannedToJSON(RandomEvents plugin, BannedPlayers bp) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(bp);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating banned players");
		}
		return resultado;

	}

	public static BannedPlayers fromJSONToBanned(RandomEvents plugin, BufferedReader br) {
                BannedPlayers bp = null;
                try {
                        bp = GSON.fromJson(br, BannedPlayers.class);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading banned players: " + br);
		}
		return bp;
	}
	
	public static String fromDisabledPlayersToJSON(RandomEvents plugin, PlayersDisabled bp) {
		String resultado = null;

                try {
                        resultado = GSON.toJson(bp);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at creating disabled players");
		}
		return resultado;

	}
	public static PlayersDisabled fromJSONToDisabledPlayers(RandomEvents plugin, BufferedReader br) {
                PlayersDisabled bp = null;
                try {
                        bp = GSON.fromJson(br, PlayersDisabled.class);

		} catch (Exception e) {
			plugin.getLoggerP().info(e.getMessage());
			plugin.getLoggerP().info("Error :: Error at loading disabled players: " + br);
		}
		return bp;
	}

}
