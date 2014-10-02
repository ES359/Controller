package controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

public class KickMsgCommand implements CommandExecutor{

	SettingsManager sm = SettingsManager.getControllerInstance();
	Info info = new Info();
			
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("kickmsg")) {
			if(!sender.hasPermission("Controller.cmd.whitelistmsg")){
				sender.sendMessage(Info.prefix_Permission + info.permissionError());
				return true;
			}else {
				String motd = sm.getConfig().getString("kick-message");
				motd = motd.replaceAll("&", "§");
				sender.sendMessage( ChatColor.DARK_GREEN +" Kick message: " + motd);
				return true;
			}
		}
		return true;
	}
}
