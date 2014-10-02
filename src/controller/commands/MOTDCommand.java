package controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

public class MOTDCommand implements CommandExecutor{
	
	SettingsManager sm = SettingsManager.getControllerInstance();
	
	Info info = new Info();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("motd")) {
			if(!sender.hasPermission("Controller.cmd.motd")) {
				sender.sendMessage(Info.prefix_Permission + info.permissionError());
				return true;
			}
			
			String motd = sm.getConfig().getString("motd.ingame");
			motd = motd.replaceAll("&", "§");
			String system = sm.getConfig().getString("motd.server");
			system = system.replaceAll("&", "§");
			sender.sendMessage(ChatColor.GREEN + "InGame MOTD: " + motd);
			sender.sendMessage(ChatColor.GREEN + "Server MOTD: " + system);
			return true;
		}
		
		return true;
		
	}
}
