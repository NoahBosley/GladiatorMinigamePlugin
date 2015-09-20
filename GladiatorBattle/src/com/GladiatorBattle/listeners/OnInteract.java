package com.GladiatorBattle.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.GladiatorBattle.minigame.Arena;
import com.GladiatorBattle.minigame.ArenaManager;

public class OnInteract implements Listener{
	
	private int timesOneClicked;
	private int timesTwoClicked;

	@EventHandler
	public void onClickedSign(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				Arena arena = ArenaManager.arenaManager.getArena("TestArena");
				if(sign.getLine(0).equalsIgnoreCase("[TestArena]") && sign.getLine(1).equalsIgnoreCase("[TEAMONE]")) {
					if(timesOneClicked == 1) {
						Player player = event.getPlayer();
						player.teleport(arena.getTeamOne());
					}
				}
				if(sign.getLine(0).equalsIgnoreCase("[TestArena]") && sign.getLine(1).equalsIgnoreCase("[TEAMTWO]")) {
					if(timesTwoClicked == 3) {
						Player player = event.getPlayer();
						player.teleport(arena.getTeamTwo());
					}
				}
			}
		}
	}
}
