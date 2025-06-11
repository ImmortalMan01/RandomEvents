package com.immortalman01.randomevents.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.immortalman01.randomevents.match.MatchActive;

public class ReventSpawnEvent extends Event {

	private final MatchActive matchActive;

	private final Boolean forced;

	public ReventSpawnEvent(MatchActive matchActive, Boolean forced) {
		super();
		this.matchActive = matchActive;
		this.forced = forced;
	}

	private static final HandlerList HANDLERS = new HandlerList();

	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	public MatchActive getMatchActive() {
		return matchActive;
	}

	public Boolean getForced() {
		return forced;
	}

}