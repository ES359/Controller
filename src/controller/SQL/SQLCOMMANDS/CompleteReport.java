package controller.SQL.SQLCOMMANDS;

import com.enjin.es359.SettingsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 3/16/15.
 */

@Deprecated
public class CompleteReport implements CommandExecutor {


    Report yes = new Report();
    SettingsManager sm = SettingsManager.getControllerInstance();

    public void completeReport(Player p, String s) {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase(""))
        {
            String uuid = ""+p.getUniqueId();

            if(!uuid.equals(sm.getAuthor())){
                p.sendMessage("...");
            }else {
                if(args.length < 0) {
                    p.sendMessage("/complete [id] <Completion message. [150 char limit.]>");
                }else if(args.length > 0) {
                    if(args.length == 1) {
                        p.sendMessage("/complete [id] < message >");
                    }else if(args.length > 1){

                        String id = args[0];


                        StringBuilder str = new StringBuilder();

                        for(int j = 0; j <args.length; j++) {
                            str.append(args[j] + " ");
                        }


                        String sql = str.toString().replaceAll(args[0],"");



                    }
                }
            }
        }
        return false;
    }

}

