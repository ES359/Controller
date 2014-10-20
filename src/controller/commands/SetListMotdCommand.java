package controller.commands;

import com.enjin.es359.Inform;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.SettingsManager;

public class SetListMotdCommand extends Inform implements CommandExecutor{



	SettingsManager sm = SettingsManager.getControllerInstance();
	

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("setlistmotd")) {
			if(!sender.hasPermission("Controller.cmd.setlistmotd")) {
				sender.sendMessage(prefix_Permission + permissionError());
				return true;
			}
			
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED +"Please enter the msg you wish to broadcast on server ping.");
				sender.sendMessage(ChatColor.AQUA +"This message will be sent to all players who ping the server in their list.");
				sender.sendMessage(ChatColor.GREEN +"This plugin supports all color codes and formatting: " +ChatColor.RESET + ChatColor.YELLOW + "&1,&2,&3... &k,&l,&n...etc.");	
				return true;
			}
			StringBuilder str = new StringBuilder();
			
			for(int i =0; i <args.length; i++) {
				str.append(args[i] + " ");
			}
			String motd = str.toString();
			
			sm.getConfig().set("motd.server", motd);
			sm.saveConfig();
			String system = sm.getConfig().getString("motd.server");
			system = system.replaceAll("&", "§");
			sender.sendMessage(ChatColor.GREEN + "MOTD set to: " + system);
			return true;
		}
		
		return true;
	
	}
	
}
