package me.OmegaOoof.ShardSkyblock.Main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.OmegaOoof.ShardSkyblock.Main.Bosses.Boss1;
import me.OmegaOoof.ShardSkyblock.Main.Economy.EconCommand;
import me.OmegaOoof.ShardSkyblock.Main.Economy.EconManager;
import me.OmegaOoof.ShardSkyblock.Main.Economy.PlayerJoin;
import me.OmegaOoof.ShardSkyblock.Main.Economy.SLAPI;
import me.OmegaOoof.ShardSkyblock.Main.MOTD.ServerMOTD;
import me.OmegaOoof.ShardSkyblock.Main.Scoreboard.ScoreboardManagerClass;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

public class ShardSkyblock extends JavaPlugin implements Listener{

	private PluginManager pm = Bukkit.getServer().getPluginManager();
	
	  private static final Logger log = Logger.getLogger("Minecraft");
	    private static Economy econ = null;
	    private static Permission perms = null;
	    private static Chat chat = null;


	
	public void onEnable() {
		
		getCommand("Shards").setExecutor(new EconCommand());
		new EconManager(this);
		SLAPI.loadBalances();
		
		pm.registerEvents(new PlayerJoin(), this);
		
		pm.registerEvents(new ScoreboardManagerClass(), this);
		
		//Letting the operators of console know that the plugin has been ENABLED.
		Bukkit.getServer().getLogger().info("ShardSkyblock Core Has been Enabled.");
		
		addDefaultConfig();
		addCommands();
		saveMainConfig();
		addEvents();
		
		
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

		
		
		
	}

	public void onDisable() {
		SLAPI.saveBalances();
		//Letting the operators of console know that the plugin has been DISABLED.
		Bukkit.getServer().getLogger().info("ShardSkyblock Core Has been Disabled.");
		 log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
	}
	
	private void addDefaultConfig()
	{
		//This chunk of code adds new Default options to the configuation file.
		this.getConfig().addDefault("MOTD.Prefix", "&3&k::&b&lShardSkyblock&3&k::&8&l>");
		this.getConfig().addDefault("MOTD.Message", "&eEdit this default message in the config.");
		this.getConfig().addDefault("Commands.Prefix", "&8&l[&b&lShard&3&lSkyblock&8&l]");
	}
	
	private void addCommands()
	{
	    //Registering all of the commands from other classes.
		getCommand("ShardBosses").setExecutor(new Boss1(null));
		

	}
	
	private void addEvents()
	{
		//Registering all of the events from other classes.
	    pm.registerEvents(new ServerMOTD(null), this);
	    pm.registerEvents(new Boss1(null), this);
	    pm.registerEvents(this, this);
	}
	
	private void saveMainConfig()
	{
		//This saves the config file.
	    this.saveConfig();
	}
	
	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		
		if(e.getPlayer().hasPermission("ShardSkyblock.Staff")) {
			e.getPlayer().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Shard" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Skyblock" + ChatColor.GRAY + " Developed by OmegaOoof");
			e.getPlayer().sendMessage(ChatColor.GRAY + "Services are available at: " + ChatColor.RED + "https://www.fiverr.com/omegaooof");
		}
		
	}
	
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            log.info("Only players are supported for this Example Plugin, but you should not do this!!!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if(command.getLabel().equals("test-economy")) {
            // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
            sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
            EconomyResponse r = econ.depositPlayer(player, 1.05);
            if(r.transactionSuccess()) {
                sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
            } else {
                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
            }
            return true;
        } else if(command.getLabel().equals("test-permission")) {
            // Lets test if user has the node "example.plugin.awesome" to determine if they are awesome or just suck
            if(perms.has(player, "example.plugin.awesome")) {
                sender.sendMessage("You are awesome!");
            } else {
                sender.sendMessage("You suck!");
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }
    
    public static Chat getChat() {
        return chat;
    }
    
    public static double  getMoney(Player p) {
        double m = econ.getBalance(p);
        return m;
    }
	

}
