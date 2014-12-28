package controller.events;

import com.enjin.es359.Inform;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.enjin.es359.Controller;
import com.enjin.es359.SettingsManager;
//Add whitelist features next.
//Fix join and quit logic.

public class CPMenuEvent extends Inform

        implements Listener{


    /**
     * Permission registration.
     */


    SettingsManager sm = SettingsManager.getControllerInstance();
    ChatEvent ch = ChatEvent.getChatInstance();
    DisconnectPlayer dp = new DisconnectPlayer();

	public ItemStack day,night,sun,rain,pList,reload,reloadConfig,stop,whitelist,motd,whitelistmsg,chat, disconnect;
	
	public Controller c;
	
	Inventory cp;
	public CPMenuEvent(Controller instance, Plugin p) {
		c = instance;
		
		cp = Bukkit.getServer().createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', "&4&oAdmin &6&lControl &aPanel."));
		
		
	Bukkit.getServer().getPluginManager().registerEvents(this, p);

	
		 
		sun = SettingsManager.createItem(Material.DAYLIGHT_DETECTOR, ChatColor.translateAlternateColorCodes('&', "&eSets the server Weather to clear!"));
		rain = SettingsManager.createItem(Material.WATER_BUCKET, ChatColor.translateAlternateColorCodes('&', "&bSet the weather to &9&orainy."));
		day = SettingsManager.createItem(Material.WATCH, ChatColor.translateAlternateColorCodes('&', "&6Sets the server time to day!"));
		night = SettingsManager.createItem(Material.NETHER_BRICK, ChatColor.translateAlternateColorCodes('&', "&aSet the server to &9night!"));
		pList = SettingsManager.createItem(Material.SKULL_ITEM, ChatColor.translateAlternateColorCodes('&', "&3&lList all the players online the server!"));
		reload = SettingsManager.createItem(Material.LAVA_BUCKET, ChatColor.translateAlternateColorCodes('&', "&4&lRELOADS THE SERVER."));
		reloadConfig = SettingsManager.createItem(Material.ENCHANTED_BOOK, ChatColor.translateAlternateColorCodes('&', "&6&lReloads the plugin's configuration."));
		stop = SettingsManager.createItem(Material.BEDROCK, ChatColor.translateAlternateColorCodes('&', "&4&l&oSTOPS THE SERVER."));
		whitelist = SettingsManager.createItem(Material.PAPER, ChatColor.translateAlternateColorCodes('&', "&7Enables or disables &8Whitelist."));
		whitelistmsg =SettingsManager.createItem(Material.BOOK, ChatColor.translateAlternateColorCodes('&', "&2&oShows the current whitelist message."));
		motd = SettingsManager.createItem(Material.MAP, ChatColor.translateAlternateColorCodes('&', "&bShows the motd."));
		chat = SettingsManager.createItem(Material.WRITTEN_BOOK, ChatColor.translateAlternateColorCodes('&', "&9Mutes or unmutes the &b&oChat."));
        disconnect = SettingsManager.createItem(Material.ACTIVATOR_RAIL, ChatColor.translateAlternateColorCodes('&', "&6Disconnects &cyou from the server."));

		cp.setItem(0, sun);
		cp.setItem(1, rain);
		cp.setItem(2, day);
		cp.setItem(3, night);
		cp.setItem(4, pList);
		cp.setItem(9, reload);
		cp.setItem(10, stop);
		cp.setItem(11, reloadConfig);
		cp.setItem(12, whitelist);
		cp.setItem(13, whitelistmsg);
		cp.setItem(14, motd);
        cp.setItem(15, chat);
        cp.setItem(16,disconnect);
		cp.setItem(35, SettingsManager.closeMenuItem());
	}
	
	public void showInventory(Player p) {
		p.openInventory(cp);
	}


	
	@EventHandler
	public void onInvCLick(InventoryClickEvent event) {
		
		Player p = (Player)event.getWhoClicked();
		
		/**
		if(event.getCurrentItem().equals(day) || event.getCurrentItem().equals(night) || event.getCurrentItem().equals(rain) || event.getCurrentItem().equals(sun) || event.getCurrentItem().equals(stop) || event.getCurrentItem().equals(reload) || event.getCurrentItem().equals(reloadConfig)) {
			if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
				event.setCancelled(true);
				return;
			}
			return;
		}
		*/

        if(event.getCurrentItem().equals(disconnect)) {
               if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
                   event.setCancelled(true);
                   dp.disconnect(p);
               }
            }


//Chat.Inform.
        if(event.getCurrentItem().equals(chat)) {

            if(!p.hasPermission("Controller.cp.admin.chat")) {
                p.closeInventory();
                p.sendMessage(prefix_Permission + permissionError());
            }else {
                if(event.isRightClick()) {
                    event.setCancelled(true);
                    ch.setChatEnabled(false);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin +"&cGlobal chat has been &a&oUnmuted."));
                    Bukkit.getServer().broadcastMessage(chatInformEnabled());
                }else if(event.isLeftClick()) {
                    event.setCancelled(true);
                    ch.setChatEnabled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin +"&cGlobal Chat has been &c&oMuted."));
                    Bukkit.getServer().broadcastMessage(chatinformDisabled());
                }
            }
        }

		if(event.getCurrentItem().equals(SettingsManager.closeMenuItem())) {
			if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
				SettingsManager.closeMenu(p);
			}
		}

//MOTD.
		if(event.getCurrentItem().equals(motd)) {
			if(!p.hasPermission("Controller.cp.admin.motd")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					p.closeInventory();
					String motd = sm.getConfig().getString("motd.ingame");
					motd = motd.replaceAll("&", "§");
					String system = sm.getConfig().getString("motd.server");
					system = system.replaceAll("&", "§");
					p.sendMessage(ChatColor.GREEN + "InGame MOTD: " + motd);
					p.sendMessage(ChatColor.GREEN + "Server MOTD: " + system);
					
				}
			}
		}
		
//WHITELIST
		if(event.getCurrentItem().equals(whitelistmsg)) {
			if(!p.hasPermission("Controller.cp.admin.whitelistmsg")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					p.closeInventory();
					String motd = sm.getConfig().getString("kick-message");
					motd = motd.replaceAll("&", "§");
					p.sendMessage( ChatColor.DARK_GREEN +" Kick message: " + motd);
					return;
				}
			}
		}
		//
		if(event.getCurrentItem().equals(whitelist)) {
			if(!p.hasPermission("Controller.cp.admin.whitelist")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() ) {
					event.setCancelled(true);
					Bukkit.getServer().setWhitelist(false); 
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin + "&7White list has been &c&oDisabled."));
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Console + "&6&lWhitelist has been &cdisabled &6&lby player, &e" + p.getName()));
				}else if(event.isLeftClick()) {
					event.setCancelled(true);
					Bukkit.getServer().setWhitelist(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin + "&7White list has been &a&oEnabled."));
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix_Console + "&6&lWhitelist has been &aEnabled &6&lby player, &e" + p.getName()));
					return;
				}
			}
		}
		
		
		if(event.getCurrentItem().equals(day)) {
			
			if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
				event.setCancelled(true);
				
			if(!p.hasPermission("Controller.cp.day")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission +permissionError());
				return;
			}else {
					p.getWorld().setTime(1000);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin+"&6&lThe time in the &bWorld: " + p.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oDay!")));
					p.closeInventory();
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(night)) {
			if(!p.hasPermission("Controller.cp.night")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission +permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
					
					p.getWorld().setTime(13000);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix_Plugin+"&6&lThe time in the &bWorld: " + p.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &9&oNight!")));
					p.closeInventory();
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(sun)) {
			if(!p.hasPermission("Controller.cp.sun")) {
				p.closeInventory();
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
					p.getWorld().setStorm(false);
					//player.getWorld().setWeatherDuration(100);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin+"&6&lThe Weather in the &bWorld: " + p.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oClear!")));
					p.closeInventory();
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(rain)) {
			if(!p.hasPermission("Controller.cp.rain")) {
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
					p.getWorld().setStorm(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin+"&6&lThe Weather in the &bWorld: " + p.getWorld().getName() + ChatColor.translateAlternateColorCodes('&', " &ehas been set to &6&oRainy!")));
					p.closeInventory();
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(pList)) {
			if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
				event.setCancelled(true);
				
				
				for(Player online : Bukkit.getOnlinePlayers()) {
					p.closeInventory();
					p.sendMessage("" +ChatColor.AQUA + online.getName() +ChatColor.YELLOW + ", "+ChatColor.AQUA);
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(stop)) {
			if(!p.hasPermission("Controller.cp.admin.stop")) {
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
					p.closeInventory();
					Bukkit.getServer().shutdown();
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin+"&4&oCP &a&l> &c&oShutting down..."));
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(reload)) {
			if(!p.hasPermission("Controller.cp.admin.reload")) {
				event.setCancelled(true);
				
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isLeftClick() || event.isRightClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
					p.closeInventory();
					Bukkit.getServer().reload();
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',prefix_Plugin+" &6&oServer is &c&nRestarting...!"));
					return;
				}
			}
		}
		if(event.getCurrentItem().equals(reloadConfig)) {
			if(!p.hasPermission("Controller.cp.admin.config")) {
				p.sendMessage(prefix_Permission + permissionError());
				return;
			}else {
				if(event.isRightClick() || event.isLeftClick() || event.isShiftClick()) {
					event.setCancelled(true);
					
				p.closeInventory();
				sm.reloadConfig();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &eServer &6Configuration &cFile &bhas been &a&oReloaded..."));
				return;
				}
			}
		}
	}
	
}
