package com.GladiatorBattle.commands;


import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.GladiatorBattle.minigame.ArenaManager;

public class Commands implements CommandExecutor {
	
	private Location lobby;
	private Location teamOne;
	private Location teamTwo;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args[0].equalsIgnoreCase("setlobby") && sender instanceof Player) {
			Player player = (Player) sender;
			lobby = player.getLocation();
		}
		if(args[0].equalsIgnoreCase("setteamone") && sender instanceof Player) {
			Player player = (Player) sender;
			teamOne = player.getLocation();
		}
		if(args[0].equalsIgnoreCase("setteamtwo") && sender instanceof Player) {
			Player player = (Player) sender;
			teamTwo = player.getLocation();
		}
		if(args[0].equalsIgnoreCase("createArena") && sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 2) {
				player.sendMessage(ChatColor.RED + "Please specify a command.");
				return false;
			}
			ArenaManager.arenaManager.createArena(lobby, teamOne, teamTwo, args[1]);
			player.sendMessage(ChatColor.GREEN + "The arena " + args[1] + ChatColor.GREEN + " has just been created!");
			return true;
		}
		if(args[0].equalsIgnoreCase("NumberOfArenas")) {
			Player player = (Player) sender;
			if(args.length > 1) {
				player.sendMessage(ChatColor.RED + "Please specify a command.");
				return false;
			}
			player.sendMessage(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + String.valueOf(ArenaManager.arenaSize));
		}
		return false;
	}

}
