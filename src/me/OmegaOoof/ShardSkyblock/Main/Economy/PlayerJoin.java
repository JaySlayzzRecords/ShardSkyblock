package me.OmegaOoof.ShardSkyblock.Main.Economy;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getPlayer().getName());

		if(!offlinePlayer.hasPlayedBefore())
		{
			EconManager.setBalance(event.getPlayer().getName(), 0D);
		}else {
			EconManager.setBalance(event.getPlayer().getName(), EconManager.getPlugin().getConfig().getDouble("Balances." + event.getPlayer().getName()));
		}
		
		
		
	}

}
