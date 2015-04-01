package controller.SQL.SQLCOMMANDS;

import com.enjin.es359.Controller;
import com.enjin.es359.Inform;
import com.enjin.es359.Timestamp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ES359 on 3/1/15.
 */
public class Query extends Inform implements CommandExecutor{

    public Controller main;

    public Query(Controller instance) {
        this.main = instance;
    }

    private void Test(String ip, String userName, String access, String db, CommandSender user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            /*
      ADDITIONS. ADD A TEST CONNECTION COMMAND. CREATE A COMMAND TO ADD A NEW CONNECTION. ETC.
     */
            Connection c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
            //this.globalBroadcast("CONNECTION WORKED!");
            user.sendMessage("Connection to database parameters: " + ChatColor.AQUA +"[ "+ip + ", " + userName +", " + access +", " + db + " ] Was successful!");
            logToConsole("Connection test was &csuccessful!");
        } catch (Exception e) {
            e.printStackTrace();
           // this.globalBroadcast("CONNECTION FAILED.");
            logToConsole(connectionErrorInform());
            user.sendMessage("Connection to database parameters: " + ChatColor.RED + "[ " + ip + ", " + userName + ", " + access + ", " + db + " ] Was NOT successful!");
        }
    }

    public Timestamp stamp = new Timestamp();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {

        if(this.main.getEnabled() == true) {

            if(cmd.getName().equalsIgnoreCase("sql")) {
                if(!sender.hasPermission("SQL.execute")) {
                    sender.sendMessage(ChatColor.YELLOW +"You don't have permission " +sender.getName()+".");
                }else {

                    if(args.length < 1) {
                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.DARK_GRAY+"--------------------");
                        sender.sendMessage(ChatColor.GRAY+"SQL features/commands.");
                        sender.sendMessage("/sql <function> <none>");
                        sender.sendMessage("/sql <help>");
                        sender.sendMessage("/sql <admin>");
                        sender.sendMessage("/sql [test] <Connection_Parameters>");
                        sender.sendMessage("=========================");
                        sender.sendMessage("Permissions:");
                        sender.sendMessage("SQL.execute - Allows user to run SQL Query's and access database functions.");
                        sender.sendMessage("SQL.function.admin - Help directory for Database Administrators.");
                        sender.sendMessage("---------------");
                        //sender.sendMessage("Options -/sql <query> <sql>/sql <data> <sql>");
                    }else {

                        if(args[0].equalsIgnoreCase("query"))
                        {
                            if(args.length == 1) {
                                sender.sendMessage("/sql <query> <sql>");
                            }else {
                                if(args.length > 1) {
                                    StringBuilder str = new StringBuilder();

                                    for (String arg : args) {
                                        str.append(arg + " ");
                                    }
                                    String query = str.toString().replaceAll("query","");

                                    try {
                                        PreparedStatement statement = this.main.sql.getConnection().prepareStatement(query);
                                        statement.execute();
                                        statement.close();
                                        sender.sendMessage(ChatColor.RED + "Executed Query: ["+ChatColor.YELLOW + query + ChatColor.RED +"]");
                                        logToConsole("&fUSER, " + sender.getName() + " &fExecuted the query: " + query);
                                    }catch (SQLException sql){
                                        sql.printStackTrace();

                                        sender.sendMessage(ChatColor.RED +"QUERY FAILED - CHECK CONSOLE FOR STACKTRACE.");
                                    }
                                }
                            }
                        }else if(args[0].equalsIgnoreCase("help"))
                        {
                            sender.sendMessage("");
                            sender.sendMessage(ChatColor.DARK_GRAY+"--------------------");
                            sender.sendMessage(ChatColor.GRAY +"Controller SQL help page:");
                            sender.sendMessage(ChatColor.GRAY+"Commands. - Functions that are surrounded with\"[]\", are required.\nFunctions surrounded with \"<>\", are sometimes not required. ");
                            sender.sendMessage("/sql [query] <sql> - Allows you to write most sql query's. SEE EXAMPLES.");
                            sender.sendMessage("/sql [function] - Due to Bukkits limited visual interface, some SQL features aren't available. They will be hard coded.");
                            sender.sendMessage("/sql [examples] - Examples of Query's that can be executed.");
                            sender.sendMessage("/sql [admin] - Admin related functions.");
                            return true;
                        }else if(args[0].equalsIgnoreCase("examples"))
                        {
                            sender.sendMessage("");
                            sender.sendMessage(ChatColor.DARK_GRAY+"--------------------");
                            sender.sendMessage(ChatColor.GRAY+"SQL Examples. ");
                            sender.sendMessage(ChatColor.GRAY+"Since minecraft visuals are limited, the only sql functions that will really make sense to use are database functions.");
                            sender.sendMessage(ChatColor.GRAY+"All querys must use the command syntax, /sql [query] <sql> In order to be executed.");
                            sender.sendMessage("Adding ID tags to tables:"+ChatColor.GREEN+" ALTER TABLE [table_name] ADD id INT PRIMARY KEY AUTO_INCREMENT FIRST;");
                            sender.sendMessage("Creating a table example: "+ChatColor.GREEN+" CREATE TABLE IF NOT EXISTS [table_name] ( <column_name> varchar(25), <ip> varchar(30));");
                            sender.sendMessage("Removing a table: "+ChatColor.GREEN+" DROP TABLE [table_name];");
                            sender.sendMessage("Deleting rows or data: "+ChatColor.GREEN+" DELETE * FROM [table_name] WHERE [Column_name] = '[value]'; ");
                            sender.sendMessage("Inserting data into tables: "+ChatColor.GREEN+" INSERT INTO [table_name] [ ('Column1','Column2') ] VALUES ('Test1','test2'); ");
                        }else if(args[0].equalsIgnoreCase("admin"))
                        {
                            if(!sender.hasPermission("SQL.function.admin")) {
                                sender.sendMessage(ChatColor.YELLOW +"This section requires the rank "+ChatColor.RED + "Administrator or Above.");
                            }else {
                                sender.sendMessage("");
                                sender.sendMessage(ChatColor.DARK_GRAY+"--------------------");
                                sender.sendMessage(ChatColor.RED +"Administration Controls/options.");
                                sender.sendMessage("Haven't figured out what is going here yet.");
                                sender.sendMessage("Possibly direct access to php login?");
                            }
                        }else if(args[0].equalsIgnoreCase("test")) {

                            //  107.170.21.151 user password Logs

                            //String ip, String userName, String access, String db

                            String params[] = new String[10];

//                                params[0]

                            if(args.length == 1) {
                                sender.sendMessage("/sql [test] [connection] [<Address> <username> <password> <database>] - Test an SQL Connection.");
                            }else if(args.length > 1) {
                                params[0] = args[2];
                                params[1] = args[3];
                                params[2] = args[4];
                                params[3] = args[5];
                                this.Test(params[0],params[1],params[2],params[3],sender);
                            }
                        }else if(args[0].equalsIgnoreCase("time"))
                        {
                           sender.sendMessage(ChatColor.GRAY + "Current time is: " + ChatColor.GOLD +"" +stamp.getStamp());
                        }
                    }
                }
            }
        }else {
            sender.sendMessage(SQLIsDisabled());
        }
        return true;
    }
}
