package me.BOBNOTBUILDING.Hub;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class spawnleave extends JavaPlugin implements Listener{

		public final Logger logger = Logger.getLogger("Minecraft");

		@Override
		public void onDisable(){

			PluginDescriptionFile pdfFile = this.getDescription();
			this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been DISABLED!");

		}

		@Override
		public void onEnable() {

			getConfig().options().copyDefaults(true);
			saveConfig();

			getServer().getPluginManager().registerEvents(this,this);
			PluginDescriptionFile pdfFile = this.getDescription();

			this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been ENABLED!");

		}


		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 360000, this.getConfig().getInt("SpeedStrength")));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 360000, this.getConfig().getInt("JumpStrength")));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 360000, this.getConfig().getInt("SpeedStrength")));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 360000, this.getConfig().getInt("SpeedStrength")));
    }
		
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
			Player player = (Player) sender;

			if(label.equalsIgnoreCase("hreload")){
				if(player.hasPermission("hub.reload")) {
				reloadConfig();

				sender.sendMessage(ChatColor.GREEN + "Successfully Reloaded!");
			}
			}
			return false;
		}



		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent e){
			Player player = e.getPlayer();
			if(!player.hasPermission("hub.bypass")) {
            player.teleport(new Location(Bukkit.getWorld("world"), this.getConfig().getInt("X"), this.getConfig().getInt("Y"), this.getConfig().getInt("Z")));
			}
		}
		


}