package controller.commands;

import com.enjin.es359.Inform;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Fixed if statement bug for atomic-hive item command.
 *
 */

/**
 * Fixed by ES359 on 1/7/15.
 *
 * Created by Minecoder101. //That's what he thinks
 *
 */

public class ItemCommand extends Inform implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "The Console cannot use this command.");
            return true;
        }

        Player p = (Player)sender;


        if(cmd.getName().equalsIgnoreCase("item")) {
            if(!p.hasPermission("atomicitems.item.use")) {
                p.sendMessage(ChatColor.YELLOW + "You don't have permission to use this command, " + ChatColor.RED + p.getName());
            }else {
                if(args.length == 0) {
                    p.sendMessage(ChatColor.BLUE + "You have used the incorrect amount of arguments.");
                }else {
                    if(args.length == 1) {
                        ItemStack itemStack = new ItemStack(Material.getMaterial(Integer.parseInt(args[0])), 64);
                        p.sendMessage(ChatColor.GRAY + "Giving " + ChatColor.GOLD + "64 " + ChatColor.RED + args[0] + ChatColor.GRAY + " to " + ChatColor.GOLD + p.getName());
                        p.getInventory().addItem(itemStack);
                    }else if(args.length == 2) {
                        ItemStack itemStack = new ItemStack(Material.getMaterial(Integer.parseInt(args[0])), 64);
                        p.sendMessage(ChatColor.GRAY + "Giving " + ChatColor.GOLD + "64 " + ChatColor.RED + args[0] + ChatColor.GRAY + " to " + ChatColor.GOLD + p.getName());
                        p.getInventory().addItem(itemStack);
                    }
                }
            }
        }
        return true;
    }
}
