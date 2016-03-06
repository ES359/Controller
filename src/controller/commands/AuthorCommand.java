package controller.commands;

import com.enjin.es359.Controller;
import com.enjin.es359.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by ES359 on 3/7/15.
 */
public class AuthorCommand implements CommandExecutor {


    SettingsManager sm = SettingsManager.getControllerInstance();

    private UserCPCommand usercpc;
    private Controller main;


    public AuthorCommand(Controller cinstance, UserCPCommand uinstance) {
        this.usercpc = uinstance;
        this.main=cinstance;
    }

    public void checkAuthor()
    {

    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {


        if(!(sender instanceof Player)) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }

        Player p = (Player)sender;
        String uuid = ""+p.getUniqueId();

        if(cmd.getName().equalsIgnoreCase("auth")) {

            if(!uuid.equals(sm.getAuthor())) {
                p.sendMessage("Unknown command. Type \"/help\" for help.");
                // p.sendMessage(ChatColor.YELLOW+"Uh oh... Doesn't look like this is yours  - " + p.getName());
            }else {


                if(args.length < 1) {


                 p.sendMessage(ChatColor.GRAY+"Hello, " + p.getName() + " Please use /auth help");


                }else if(args.length > 0){

                    if(args[0].equalsIgnoreCase("manage")) {
                        if(args.length == 1) {
                            sender.sendMessage("/auth manage <user>");
                        }else {
                            Player target = Bukkit.getServer().getPlayer(args[1]);

                            if(target == null) {
                                p.sendMessage("The target player is null! Are they online? ");
                                return true;
                            }else {

                                usercpc.adminMenu(p,target);
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("help"))
                    {
                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.DARK_GRAY+"--------------------");
                        sender.sendMessage(ChatColor.GRAY +"Controller Developer help page:");
                        sender.sendMessage(ChatColor.GRAY+"Commands. - Functions that are surrounded with\"[]\", are required.\nFunctions surrounded with \"<>\", are sometimes not required. ");
                        sender.sendMessage("/auth [manage] <playername>  Allows you to manage a username.");
                        sender.sendMessage("/sql [function] - Due to Bukkits limited visual interface, some SQL features aren't available. They will be hard coded.");
                        sender.sendMessage("/sql [examples] - Examples of Query's that can be executed.");
                        sender.sendMessage("/sql [admin] - Admin related functions.");

                    }
                }
            }
        }
        return false;
    }
}
