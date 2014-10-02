package controller.commands;

import com.enjin.es359.Info;
import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import controller.events.CPMenuEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 9/10/14.
 */
public class ChatCommand implements CommandExecutor {


    SettingsManager sm = SettingsManager.getControllerInstance();
    Info i = new Info();
    Inform inform = new Inform();



    public CPMenuEvent access;

    public ChatCommand(CPMenuEvent instance) {
        access = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(cmd.getName().equalsIgnoreCase("chat")) {
            if(!sender.hasPermission("Controller.cmd.chat")) {
                sender.sendMessage(i.permissionError());
            }else {

                if(args.length == 0) {
                    sender.sendMessage(inform.ArgumentsError());
                }else if(args.length == 1) {

                    if(args[0].equalsIgnoreCase("disabled")) {
                        access.setChatEnabled(true);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin+"&cGlobal chat has been &c&odisabled &cby command."));
                        Bukkit.getServer().broadcastMessage(i.chatinformDisabled());

                    }else if(args[0].equalsIgnoreCase("enabled")) {

                        access.setChatEnabled(false);
                        //That is worded wrongggg.

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cGlobal chat has been &a&oEnabled &cby Command."));
                        Bukkit.getServer().broadcastMessage(i.chatInformEnabled());
                        return true;
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Info.prefix_argsError +"&6Unknown argument has been passed to the chat command\n&cPlease use either &aEnabled &cor disabled."));
                        //Bukkit.getServer().getConsoleSender().sendMessage(info.);
                    }
                }
            }
        }
        return true;
    }
}
