package me.OmegaOoof.ShardSkyblock.Main.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EconCommand implements CommandExecutor{

	String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&b&lShard&3&lSkyblock&8&l] ");
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length != 3) {
			sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "Incorrect Usage.");
			sender.sendMessage(prefix + ChatColor.GREEN + "Usage: " + ChatColor.GRAY + "/Shards <add/remove/set> <player> <amount>");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("add")) {
			
			if(!EconManager.hasAccount(args[1])) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "This user does not have an account.");
				return true;
			}
			double amount = 0;
			try {
				amount = Double.parseDouble(args[2]);
			}catch(Exception e) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "You must specify a number.");
				return true;
			}
			
			EconManager.setBalance(args[1], EconManager.getBalance(args[1]) + amount);
			sender.sendMessage(prefix + ChatColor.YELLOW + "Success: " + ChatColor.GRAY + "You have added " + amount + " to their total Shard count.");
			
			
		}else if(args[0].equalsIgnoreCase("remove")) {
			
			if(!EconManager.hasAccount(args[1])) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "This user does not have an account.");
				return true;
			}
			double amount = 0;
			try {
				amount = Double.parseDouble(args[2]);
			}catch(Exception e) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "You must specify a number.");
				return true;
			}
			
			EconManager.setBalance(args[1], EconManager.getBalance(args[1]) - amount);
			sender.sendMessage(prefix + ChatColor.YELLOW + "Success: " + ChatColor.GRAY + "You have removed " + amount + " from their total Shard count.");
			
		}else if(args[0].equalsIgnoreCase("set")) {
			
			if(!EconManager.hasAccount(args[1])) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "This user does not have an account.");
				return true;
			}
			double amount = 0;
			try {
				amount = Double.parseDouble(args[2]);
			}catch(Exception e) {
				sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "You must specify a number.");
				return true;
			}
			
			EconManager.setBalance(args[1], amount);
			sender.sendMessage(prefix + ChatColor.YELLOW + "Success: " + ChatColor.GRAY + "You have set their total shard count to " + amount);
			
		}else {
			sender.sendMessage(prefix + ChatColor.RED + "Error: " + ChatColor.GRAY + "Invalid arguments");
		}
		
		
		
		return true;
	}

}
