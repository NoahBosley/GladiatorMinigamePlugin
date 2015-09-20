package com.GladiatorBattle.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

public class Arena {

	private final String id;

	private final Location teamOne;
	private final Location teamTwo;
	private final Location lobby;
	private final List<UUID> players = new ArrayList<UUID>();
	
	public Arena(Location lobby, Location teamOne, Location teamTwo, String id) {
		
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.lobby = lobby;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public Location getTeamOne() {
		return teamOne;
	}

	public Location getTeamTwo() {
		return teamTwo;
	}

	public Location getLobby() {
		return lobby;
	}
	
	public List<UUID> getPlayers() {
		return players;
	}
	
}
