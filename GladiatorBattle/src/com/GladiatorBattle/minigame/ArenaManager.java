package com.GladiatorBattle.minigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaManager {

	public static  ArenaManager arenaManager;
	
	private final Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
	private final Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	
	public static List<String> allArenaIDs = new ArrayList<String>();
	
	private final ItemStack[] armorContents = {
			new ItemStack(Material.IRON_BOOTS)
			, new ItemStack(Material.IRON_LEGGINGS)
			, new ItemStack(Material.IRON_CHESTPLATE)
			, new ItemStack(Material.IRON_HELMET)
	};
	
	private final ItemStack[] invContents = {
			new ItemStack(Material.STONE_SWORD)
			, new ItemStack(Material.GOLDEN_APPLE, 2)
			, new ItemStack(Material.COOKED_BEEF, 16)
	};
	
	public final static List<Arena> arenas = new ArrayList<Arena>();
	public static int arenaSize = 0;
	
	private ArenaManager() {}
	
	public ArenaManager getManager() {
		if(this.arenaManager == null)
			this.arenaManager = new ArenaManager();
		
		return this.arenaManager;	
	}
	
	public Arena getArena(String i) {
		for(Arena a : this.arenas) {
			if(a.getId() == i) {
				return a;
			}
		}
		return null;
	}
	
	public void addPlayer(Player player, String i) {
		Arena arena = this.getArena(i);
		if(arena == null) {
			player.sendMessage("Invalid arena!");
			return;
		}
		if(this.isInGame(player)) {
			player.sendMessage("Cannot join more than 1 game!");
			return;
		}
		
		arena.getPlayers().add(player.getUniqueId());
		
		inv.put(player.getUniqueId(), player.getInventory().getContents());
		armor.put(player.getUniqueId(), player.getInventory().getArmorContents());

		player.teleport(arena.getLobby());
		
		player.getInventory().setArmorContents(armorContents);
		player.getInventory().setContents(invContents);
		
		GamePlayer.gamePlayers.add(new GamePlayer(player));
	}
	
	public  void removePlayer(Player player) {
		Arena arena = null;
		for(Arena a : this.arenas) {
			if(a.getPlayers().contains(player.getUniqueId())) {
				arena = a;
			}
		}
		if(arena == null) {
			player.sendMessage("Invalid operation!");
			return;
		}
		
		arena.getPlayers().remove(player.getName());
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.getInventory().setArmorContents(armor.get(player.getName()));
		player.getInventory().setContents(inv.get(player.getName()));
		
		inv.remove(player.getUniqueId());
		armor.remove(player.getUniqueId());
		
		player.teleport(arena.getLobby());
		
		GamePlayer.gamePlayers.remove(player);
		
		player.setFireTicks(0);
	}
	
	public Arena createArena(Location lobby, Location teamOne, Location teamTwo, String id) {
		this.arenaSize++;
		
		Arena arena = new Arena(lobby, teamOne, teamTwo, id);
		this.arenas.add(arena);
		this.allArenaIDs.add(id);
		
		return arena;
	}
	
	//DELETE THIS METHOD AFTER TEST
	public boolean isPlaying() {
		int players = this.arenaManager.getArena("TestArena").getPlayers().size();
		if(players == 4) {
			return true;
		}
		return false;
	}
	
	public boolean isInGame(Player player) {
		for(Arena a : this.arenas) {
			if(a.getPlayers().contains(player.getUniqueId())) {
				return true;
			}
		}
		return false;
	}
	
}
