package me.OmegaOoof.ShardSkyblock.Main.Economy;

import java.util.HashMap;

import me.OmegaOoof.ShardSkyblock.Main.ShardSkyblock;

public class EconManager {
	
	private static ShardSkyblock plugin;
	public EconManager(ShardSkyblock instance) {
		plugin = instance;
	}
	
	public static HashMap<String, Double> bal = new HashMap<>();
	
	public static void setBalance(String player, double amount) {
		bal.put(player, amount);
	}
	
	public static Double getBalance(String player) {
		return bal.get(player);
	}
	
	public static boolean hasAccount(String player) {
		return bal.containsKey(player);
	}

	public static HashMap<String, Double>getBalanceMap(){
		return bal;
	}
	
	public static ShardSkyblock getPlugin() {
		return plugin;
	}
	
}
