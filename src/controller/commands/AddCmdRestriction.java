package controller.commands;

import com.enjin.es359.Functions;
import com.enjin.es359.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 2/20/15.
 */
public class AddCmdRestriction extends Functions implements CommandExecutor{


    SettingsManager sm = SettingsManager.getControllerInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(cmd.getName().equalsIgnoreCase("add")) {
            if(!sender.hasPermission("Controller.Function.addcmd")) {
                sender.sendMessage(permissionError());
            }else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.YELLOW +"Usage: " + ChatColor.WHITE +"/addcmd <Restricted_Command>");
                }else if(args.length > 0) {
                    String var = args[0];

                    if(var.equalsIgnoreCase(args[0])){
                        sm.getConfig().set("blocked-cmds",var);
                        sender.sendMessage("Added the command, " + var + " to restricted list!");
                    }
                }
            }
        }
        return true;
    }
}
