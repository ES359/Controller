package controller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.enjin.es359.Controller;
import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;

import controller.events.CPMenuEvent;

public class CPCommand implements CommandExecutor{

	SettingsManager sm = SettingsManager.getControllerInstance();
	Info info = new Info();
	
	public Controller a;
	
	public CPMenuEvent cp;
	
	public CPCommand( CPMenuEvent cpm) {
		
		cp = cpm;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Info.prefix_Console +info.commandError());
			return true;
		}
	 	
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("cp")) {
			if(!p.hasPermission("Controller.cmd.cp")) {
				sender.sendMessage(Info.prefix_Permission + info.permissionError());
				return true;
			}else {
				if(args.length > 0) {
					p.sendMessage(Info.prefix_argsError + info.commandError());
					return true;
				}else if(args.length == 0) {
					
					cp.showInventory(p);
					
					//p.sendMessage(cpMenu.);
					return true;
				}
			}
		}
		return true;
	}
	
}
