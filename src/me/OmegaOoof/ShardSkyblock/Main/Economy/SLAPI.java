package me.OmegaOoof.ShardSkyblock.Main.Economy;

import me.OmegaOoof.ShardSkyblock.Main.ShardSkyblock;

public class SLAPI {

	private static ShardSkyblock plugin = EconManager.getPlugin();

	public static void saveBalances() {
		for(String p : EconManager.getBalanceMap().keySet()) {
			
			plugin.getConfig().set("Balances."+p, EconManager.getBalanceMap().get(p));
			
		}plugin.saveConfig();
	}
	
	public static void loadBalances() {
		if(!plugin.getConfig().contains("Balances"))return;
		for(String s : plugin.getConfig().getConfigurationSection("Balances").getKeys(false)) {
			EconManager.setBalance(s, plugin.getConfig().getDouble("Balances."+s));
		}
	}
	
}
