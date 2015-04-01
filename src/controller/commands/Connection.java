package controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.DriverManager;

/**
 * Created by ES359 on 3/14/15.
 */
public class Connection implements CommandExecutor{

    private java.sql.Connection c;
    private void Test(String ip, String userName, String access, String db, CommandSender user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
            //this.globalBroadcast("CONNECTION WORKED!");
            user.sendMessage("Connection to database parameters: " + ChatColor.AQUA +"[ "+ip + ", " + userName +", " + access +", " + db + " ] Was successful!");
            //logToConsole("Connection test was &csuccessful!");
        } catch (Exception e) {
            e.printStackTrace();
            // this.globalBroadcast("CONNECTION FAILED.");
            //logToConsole(connectionErrorInform());
            user.sendMessage("Connection to database parameters: " + ChatColor.RED + "[ " + ip + ", " + userName + ", " + access + ", " + db + " ] Was NOT successful!");

            //user.sendMessage(ChatColor.RED+"ERROR! - Connection failed! Check your input!");
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if (cmd.getName().equalsIgnoreCase("connection")) {
           if(!sender.hasPermission("SQL.test")){
               sender.sendMessage(ChatColor.YELLOW +"You don't have permission for this, " + sender.getName());
           }else {
               if (args.length < 1) {
                   sender.sendMessage("Please use /connection test");
               } else {
                   if (args[0].equalsIgnoreCase("test")) {


                       //String ip, String userName, String access, String db

                       String params[] = new String[10];

                       if (args.length == 1) {
                           sender.sendMessage("/connection [test] [values] [<Address> <username> <password> <database>] - Test an SQL Connection.");
                       } else if (args.length > 1) {
                           params[0] = args[2];
                           params[1] = args[3];
                           params[2] = args[4];
                           params[3] = args[5];
                           this.Test(params[0], params[1], params[2], params[3], sender);
                           //sender.sendMessage("Data: " +params[0] + "\t" + params[1]+"\t" + params[2] + "\t" + params[3] );
                       }
                   }
               }
           }
        }
        return true;
    }
}
