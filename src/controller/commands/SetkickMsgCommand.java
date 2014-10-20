package controller.commands;

import com.enjin.es359.Inform;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.SettingsManager;

public class SetkickMsgCommand extends Inform implements CommandExecutor{



	SettingsManager sm = SettingsManager.getControllerInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(cmd.getName().equalsIgnoreCase("setkickmsg")) {
			if(!sender.hasPermission("Controller.cmd.setkickmsg")) {
				sender.sendMessage(prefix_Permission + permissionError());
				return true;
			}
			
			if(args.length == 0) {
				sender.sendMessage(prefix_argsError +ChatColor.RED + "Please enter the message you want.");
				sender.sendMessage( prefix_error + ChatColor.RED +"You can use the function %player% in side your message to indicate a playername.");
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
			sender.sendMessage(prefix_Plugin +ChatColor.GREEN + "Kickmessage set to: " + newMotd);
			return true;
		}
		return true;
	}
	
}
