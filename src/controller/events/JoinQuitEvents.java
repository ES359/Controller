package controller.events;

import com.enjin.es359.Inform;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.enjin.es359.SettingsManager;

public class JoinQuitEvents extends Inform implements Listener{

	SettingsManager sm = SettingsManager.getControllerInstance();


	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		String motd = sm.getConfig().getString("motd.ingame");
		motd = motd.replaceAll("&", "§");
		p.sendMessage(motd);



		
		/**
		 * 
		 * for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
			if(p.hasPermission("Controller.event.join")) {
				
				String join = sm.getConfig().getString("Join.message");
				
				join = join.replaceAll("&", "§");
				join = join.replaceAll("[PLAYERNAME]", p.getName());
				
				pl.sendMessage(join);
			}
		}
		 * 
		 */
		
		if(sm.getConfig().getBoolean("Join.enabled")) {


            String join = sm.getConfig().getString("Join.message");

            join = join.replaceAll("&", "§");
            //join = join.replaceAll("[PLAYERNAME]", p.getName());
            join = join.replaceAll("%playername%", p.getName());
            Bukkit.getServer().broadcastMessage(join);



        }
		
		
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
		/**
		 * 
		 * for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
			if(p.hasPermission("Controller.event.leave")) {
				
				
				
				pl.sendMessage(quit);
			}
		}
		 * 
		 * 
		 */
		
		if(sm.getConfig().getBoolean("Quit.enabled")) {
            String quit = sm.getConfig().getString("Quit.message");

            quit = quit.replaceAll("&", "§");
            quit = quit.replaceAll("%playername%", p.getName());

            Bukkit.getServer().broadcastMessage(quit);
        }
	}
}
