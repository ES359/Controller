package controller.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.enjin.es359.SettingsManager;

public class WhitelistEvent implements Listener{

	SettingsManager sm = SettingsManager.getControllerInstance();

	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		
		String config = sm.getConfig().getString("kick-message");
		
		config = config.replaceAll("%playername%", p.getName());
		
		if(Bukkit.getServer().hasWhitelist()) {
			if(!p.isWhitelisted()) {
				event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, ChatColor.translateAlternateColorCodes('&', config));
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN +p.getName()+ChatColor.DARK_RED  + " Was kicked from the server because they were not " + ChatColor.AQUA + "Whitelisted!");
			
			}
			
			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
				if(staff.isOp() || staff.hasPermission("Controller.event.whitelistmsg")) {
					staff.sendMessage(ChatColor.GREEN +"" + ChatColor.BOLD + "[" +ChatColor.BLUE +"Server" +ChatColor.RED +"-" +ChatColor.AQUA +"Whitelist" +ChatColor.GREEN+ "" + ChatColor.BOLD + "]" +ChatColor.YELLOW+" The player, "+ ChatColor.AQUA + p.getName() + ChatColor.RED +" " + ChatColor.ITALIC + "Tried to join the server. ");
				}else if(!staff.isOnline()) {
					return;
				}
			}
		}
	}
}
