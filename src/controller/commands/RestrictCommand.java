package controller.commands;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 9/23/14.
 */
public class RestrictCommand implements CommandExecutor{

    SettingsManager sm = SettingsManager.getControllerInstance();

    Info i = new Info();



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {


        if(cmd.getName().equalsIgnoreCase("restrict"))
        {
            if(!sender.hasPermission("Controller.cmd.Resrict"))
            {
                sender.sendMessage(i.permissionError());
                //return  true;
            }else {
                if(args.length == 0)
                {
                    sender.sendMessage(i.returnArgumentError());
                }else if(args.length == 1) {
                   Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target == null){
                        sender.sendMessage(Info.prefix_error + i.playerNotFound());
                   }

                    else {

                        if(sm.restricted.contains(target.getName()))
                        {
                            sm.restricted.remove(target.getName());
                            sender.sendMessage(i.returnRestriction() +" "+ target.getName());
                            target.sendMessage(i.returnRestrictionFalseToPlayer() +" "+target.getName());
                        }else
                        {
                            sm.restricted.add(target.getName());
                            sender.sendMessage( i.returnRestrictionTrueAdmin() + " " +target.getName());
                            target.sendMessage( i.returnRestrictionTrueToPlayer() +" "+ target.getName());
                        }
                    }
                }
            }
        }
        return false;
    }
}
