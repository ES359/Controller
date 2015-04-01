package controller.commands;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import controller.events.ChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 9/10/14.
 */
public class ChatCommand extends Inform implements CommandExecutor {


    SettingsManager sm = SettingsManager.getControllerInstance();
    ChatEvent ch = ChatEvent.getChatInstance();


    public void clearChat(){
        for(int i = 0; i < 100; i ++) {
            Bukkit.getServer().broadcastMessage("");
        }
        Bukkit.getServer().broadcastMessage("The chat has been " + ChatColor.AQUA +"Cleared.");
    }



    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(cmd.getName().equalsIgnoreCase("chat")) {
            if(!sender.hasPermission("Controller.cmd.chat")) {
                sender.sendMessage(prefix_Permission +permissionError());
            }else {

                if(args.length == 0) {
                    sender.sendMessage(ArgumentsError());
                }else if(args.length == 1) {

                    if(args[0].equalsIgnoreCase("clear")) {
                        clearChat();
                    }
                }
            }
        }
        return true;
    }
}

/*
if(args[0].equalsIgnoreCase("disabled")) {
                        ch.setChatEnabled(true);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix_Plugin+"&cGlobal chat has been &c&odisabled &cby command."));
                        Bukkit.getServer().broadcastMessage(chatinformDisabled());

                    }else if(args[0].equalsIgnoreCase("enabled")) {

                       ch.setChatEnabled(false);
                        //That is worded wrongggg.

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cGlobal chat has been &a&oEnabled &cby Command."));
                        Bukkit.getServer().broadcastMessage(chatInformEnabled());
                        return true;
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix_argsError +"&6Unknown argument has been passed to the chat command\n&cPlease use either &aEnabled &cor disabled."));
                        //Bukkit.getServer().getConsoleSender().sendMessage(info.);
                    }
 */