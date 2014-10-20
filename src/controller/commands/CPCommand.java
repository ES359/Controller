package controller.commands;

import com.enjin.es359.Inform;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.enjin.es359.Controller;
import com.enjin.es359.SettingsManager;

import controller.events.CPMenuEvent;

public class CPCommand extends Inform implements CommandExecutor{

	SettingsManager sm = SettingsManager.getControllerInstance();

	public Controller a;
	
	public CPMenuEvent cp;
	
	public CPCommand( CPMenuEvent cpm) {
		
		cp = cpm;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(prefix_Console +commandError());
			return true;
		}
	 	
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("cp")) {
			if(!p.hasPermission("Controller.cmd.cp")) {
				sender.sendMessage(prefix_Permission + permissionError());
				return true;
			}else {
				if(args.length > 0) {
					p.sendMessage(prefix_argsError + commandError());
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
