package com.GladiatorBattle.minigame;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.GladiatorBattle.main.Main;

public class GamePlayer {

	private Player player;
	
	private int kills;
	private int deaths;
	private int assist;
	
	public static List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();
	
	public GamePlayer(Player player) {
		this.player = player;
		
		//Puts the player in the configuration file
		Main.getInstance().getConfig().set(player.getName() + "." + "kills", 0);
		Main.getInstance().getConfig().set(player.getName() + "." + "deaths", 0);
		Main.getInstance().getConfig().set(player.getName() + "." + "assist", 0);
		Main.getInstance().saveConfig();
		
		kills = Main.getInstance().getConfig().getInt(player.getName() + "." + "kills");
		deaths = Main.getInstance().getConfig().getInt(player.getName() + "." + "deaths");
		assist = Main.getInstance().getConfig().getInt(player.getName() + "." + "assist");
	}
	
	public Player getPlayer() {
		return player;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssist() {
		return assist;
	}

	public double getKdr() {
		return kills / deaths;
	}
	
}
