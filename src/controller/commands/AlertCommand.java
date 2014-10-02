package controller.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

public class AlertCommand implements CommandExecutor{

	SettingsManager s = SettingsManager.getControllerInstance();
	Info info = new Info();



    /**
     * public boolean chatEnabled;

     public boolean returnChatEnabled() {
     return chatEnabled;
     }

     public void setChatEnabled(boolean val) {
     chatEnabled = val;
     }

     * @param sender
     * @param cmd
     * @param commandLabel
     * @param args
     * @return
     */



	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
	
		if(cmd.getName().equalsIgnoreCase("alert")) {
			if(!sender.hasPermission("Controller.cmd.alert")) {
				sender.sendMessage(Info.prefix_Permission +info.permissionError());
				return true;
			}else {
				if(args.length ==0) {
					sender.sendMessage(ChatColor.DARK_AQUA + " \"Correct command usage\"" + ChatColor.RED + "/alert <Msg>");
					sender.sendMessage(ChatColor.RED + "Please enter the message you wish to broadcast to the server.");
					sender.sendMessage(ChatColor.YELLOW +"This plugin supports all Minecraft Color codes and formatting.");
				}else {
					StringBuilder str = new StringBuilder();
					
					for(int j = 0; j <args.length; j++) {
						str.append(args[j] + " ");
					}
					
					String msg = str.toString();
					
					msg = msg.replace("&", "§");
					
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.getConfig().getString("AlertPrefix")) + msg);
					return true;
				}
			}
		}
		
		return true;
		
	}
	
}

