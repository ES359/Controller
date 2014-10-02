package controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

public class SetkickMsgCommand implements CommandExecutor{



	SettingsManager sm = SettingsManager.getControllerInstance();
	Info info = new Info();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("setkickmsg")) {
			if(!sender.hasPermission("Controller.cmd.setkickmsg")) {
				sender.sendMessage(Info.prefix_Permission + info.permissionError());
				return true;
			}
			
			if(args.length == 0) {
				sender.sendMessage(Info.prefix_argsError +ChatColor.RED + "Please enter the message you want.");
				sender.sendMessage( Info.prefix_error + ChatColor.RED +"You can use the function %player% in side your message to indicate a playername.");
				return true;
			}
			
			StringBuilder str = new StringBuilder();
			
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			String motd = str.toString();
			sm.getConfig().set("kick-message", motd);
			sm.saveConfig();
			String newMotd = sm.getConfig().getString("kick-message");
			
			motd = motd.replaceAll("&", "§");
			sender.sendMessage(Info.prefix_Plugin +ChatColor.GREEN + "Kickmessage set to: " + newMotd);
			return true;
		}
		return true;
	}
	
}
