package me.OmegaOoof.ShardSkyblock.Main.MOTD;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

import me.OmegaOoof.ShardSkyblock.Main.ShardSkyblock;


public class ServerMOTD implements Listener{
	//Getting an isntance of the main class so we can access the config files.
	private Plugin plugin = ShardSkyblock.getPlugin(ShardSkyblock.class);

	public ServerMOTD(ShardSkyblock plugin)
	{
		this.plugin = plugin;
	}
	
	//Setting the string 'prefix' and 'message' to their respective config options
	public String prefix = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("MOTD.Prefix"));
	public String message = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("MOTD.Message"));
	
	@EventHandler
	public void ServerMOTDEvent(ServerListPingEvent event) {
		
		event.setMotd(prefix + " " + message);
		
	}

}

