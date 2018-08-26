package me.OmegaOoof.ShardSkyblock.Main.Bosses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.OmegaOoof.ShardSkyblock.Main.ShardSkyblock;

public class Boss1 implements CommandExecutor, Listener{

	public ItemStack boss1Egg = new ItemStack(Material.PRISMARINE_CRYSTALS);
	public ItemMeta boss1EggMeta = boss1Egg.getItemMeta();

	//Getting an isntance of the main class so we can access the config files.
	private Plugin plugin = ShardSkyblock.getPlugin(ShardSkyblock.class);

	public Boss1(ShardSkyblock plugin)
	{
		this.plugin = plugin;
	}	

	//Setting the string 'prefix' and 'message' to their respective 
	public String prefix = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Commands.Prefix"));

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if(label.equalsIgnoreCase("ShardBosses")) {

			if(!sender.hasPermission("ShardBosses.Give"))
			{
				sender.sendMessage(prefix + ChatColor.RED + " You do not have permissions to give " + ChatColor.AQUA + "Shard" + ChatColor.DARK_AQUA + "Bosses");
				return true;
			}


			if(args.length != 2)
			{
				sender.sendMessage(prefix + ChatColor.GRAY + " That is not how you use this command.");
				sender.sendMessage(prefix + ChatColor.GREEN + " Usage: " + ChatColor.GRAY + "/ShardBosses <Target> <BossType>");
				return true;
			}

			if(args[1].equalsIgnoreCase("Fragment")) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null)
				{
					sender.sendMessage(prefix + ChatColor.GREEN + " Added 1 Boss Mob to your targets inventory.");
					boss1EggMeta.setDisplayName(prefix + ChatColor.RED + " Fragment");
					boss1Egg.setItemMeta(boss1EggMeta);

					target.getInventory().addItem(boss1Egg);
				}
				else
				{
					sender.sendMessage(prefix + ChatColor.RED + "Player "+ChatColor.YELLOW + args[0]+ChatColor.RED +" is not online");
				}
			}


		}




		return true;

	}


	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(event.getPlayer().getItemInHand().equals(null)) {
			return;
		}

		if(event.getPlayer().getItemInHand().getType().equals(Material.PRISMARINE_CRYSTALS)) {

			if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(prefix + ChatColor.RED + " Fragment")) {
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					Player p = event.getPlayer();

					IronGolem g = (IronGolem) p.getWorld().spawn(p.getLocation(), IronGolem.class);
					g.setCustomName(ChatColor.RED + "Fragment");
					g.setCustomNameVisible(true);
					g.setMaxHealth(750);
					g.setHealth(750);
					

					if(p.getItemInHand().getAmount() <= 1) {

						p.getInventory().remove(p.getItemInHand());;
						return;
					}


					event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);

					event.getPlayer().sendMessage(prefix + ChatColor.GREEN + " You placed down the boss mob.");

				}
			}
		}


		if(event.getPlayer().getItemInHand().equals(null)) {

			return;
		}




	}
	
	
	
	

}

