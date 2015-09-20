package com.GladiatorBattle.main;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.GladiatorBattle.commands.Commands;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		createConfig();
		getLogger().info("Starting up!");
		
		Commands cmd = new Commands();
		getCommand("gladiator").setExecutor(cmd);
	}
	
	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("Closing!");
	}
	
	private void createConfig() {
		try {
			if(!getDataFolder().exists())
				getDataFolder().mkdirs();
			File file = new File(getDataFolder(), "config.yml");
			if(!file.exists()) {
				getLogger().info("Creating Config!");
				saveConfig();
			} else {
				getLogger().info("Fetching Config!");
			}
		} catch(Exception e) {
			getLogger().info("The Cofig Broke!");
			e.printStackTrace();
		}
	}
	
	public static Main getInstance() {
		return instance;
	}

}
