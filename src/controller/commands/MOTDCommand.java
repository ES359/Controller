package controller.commands;

import com.enjin.es359.Inform;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.SettingsManager;

public class MOTDCommand extends Inform implements CommandExecutor{
	
	SettingsManager sm = SettingsManager.getControllerInstance();

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("motd")) {
			if(!sender.hasPermission("Controller.cmd.motd")) {
				sender.sendMessage(prefix_Permission + permissionError());
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
