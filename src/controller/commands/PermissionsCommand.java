package controller.commands;

import com.enjin.es359.Inform;
import com.enjin.es359.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 10/18/14.
 */
public class PermissionsCommand  extends Inform implements CommandExecutor {

    Permissions p = new Permissions();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(cmd.getName().equalsIgnoreCase("permissions")) {
           if(args.length == 0) {
               p.sendPermissionInfo(sender);
           }
           else if(args.length == 1) {
               if(args[0].equalsIgnoreCase("2")) {
                   p.senderPermissionInfo2(sender);
               }else if(args[0].equalsIgnoreCase("3")) {
                   p.sendPermissionInfo3(sender);
               }
           }
        }
        return false;
    }
}
