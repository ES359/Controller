package controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

public class SetMotdCommand implements CommandExecutor{



	SettingsManager sm = SettingsManager.getControllerInstance();
	
	Info info = new Info();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("setmotd")) {
			if(!sender.hasPermission("Controller.cmd.setMotd")) {
				sender.sendMessage(Info.prefix_Permission + info.permissionError());
				return true;
			}
			if(args.length ==0) {
				sender.sendMessage(ChatColor.RED +"Please enter the MSG you want to be the MOTD.");
				sender.sendMessage(ChatColor.AQUA +"This message will be sent to all players upon joining the server.");
				sender.sendMessage(ChatColor.GREEN +"This plugin supports all color codes and formatting: " +ChatColor.RESET + ChatColor.YELLOW + "&1,&2,&3... &k,&l,&n...etc.");	
				return true;
			}
			StringBuilder str = new StringBuilder();
			
			for(int i = 0; i <args.length; i++) {
				str.append(args[i] + " ");
			}
			String motd = str.toString();
			sm.getConfig().set("motd.ingame", motd);
			sm.saveConfig();
			String newMotd = sm.getConfig().getString("motd.ingame");
			
			motd = motd.replaceAll("&", "§");
			sender.sendMessage(ChatColor.GREEN + "MOTD set to: " + newMotd);
			return true;
		}
		
		return true;
	
	}
	
}
