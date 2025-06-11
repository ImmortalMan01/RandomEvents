package com.immortalman01.randomevents.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.immortalman01.randomevents.match.MatchActive;

public class ReventBeginEvent extends Event {

	private final MatchActive matchActive;

	public ReventBeginEvent(MatchActive matchActive) {
		super();
		this.matchActive = matchActive;
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

}