package me.OmegaOoof.ShardSkyblock.Main.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.OmegaOoof.ShardSkyblock.Main.ShardSkyblock;
import me.OmegaOoof.ShardSkyblock.Main.Economy.EconManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;



public class ScoreboardManagerClass implements Listener{

	
	
	
	
	
	
	
	
	private static ShardSkyblock plugin = EconManager.getPlugin();

	
	@EventHandler
	public void join(PlayerJoinEvent event){
	Player player = event.getPlayer();
	ScoreboardManager m = Bukkit.getScoreboardManager();
	Scoreboard b = m.getNewScoreboard();
	
	
	Objective o = b.registerNewObjective("Gold", "");
	o.setDisplaySlot(DisplaySlot.SIDEBAR);
	o.setDisplayName(ChatColor.AQUA +"" + ChatColor.BOLD + "Shard" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skyblock");

	Score name = o.getScore(ChatColor.WHITE + "Name: " + ChatColor.GOLD + event.getPlayer().getName());
	Score spacer1 = o.getScore(ChatColor.WHITE + "");
	Score shards = o.getScore(ChatColor.AQUA + "Shards: " + ChatColor.WHITE + EconManager.getBalance(event.getPlayer().getName()));
	Score balance = o.getScore(ChatColor.DARK_AQUA + "Balance: " + ChatColor.WHITE + plugin.getMoney(player));
	Score spacer2 = o.getScore(ChatColor.RED + "");
	Score spacer3 = o.getScore(ChatColor.BLUE + "");
	Score rank = o.getScore(ChatColor.WHITE + "Rank: " + ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(event.getPlayer()).getPrefix())); 
	
	Score IP = o.getScore(ChatColor.LIGHT_PURPLE + "" +  "  ShardSkyblock.XYZ  ");
	
	
	
	
	spacer1.setScore(7);
	rank.setScore(6);
	name.setScore(5);
	spacer2.setScore(4);
	shards.setScore(3);
	balance.setScore(2);
	spacer3.setScore(1);
	IP.setScore(0);

	

	player.setScoreboard(b);
	
	
}
	

	
	
}
