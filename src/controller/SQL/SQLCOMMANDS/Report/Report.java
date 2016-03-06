package controller.SQL.SQLCOMMANDS.Report;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import com.enjin.es359.Timestamp;
import controller.SQL.CreateSQLTables;
import controller.SQL.SQL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ES359 on 3/11/15.
 */
public class Report extends Inform implements CommandExecutor {

    public CreateSQLTables table = new CreateSQLTables();
    Timestamp stamp = new Timestamp();


    /**
     * Next feature to implement will be a function to ban a user from submitting a bugreport.
     *
     *
     */


    boolean log = false;
    private SQL sql;


    public void runConnection() {
        sql = new SQL("107.170.21.151","Logger","REQUEST1", "Logs");
        try{
            this.table.createTable(sql,"CREATE TABLE IF NOT EXISTS user_reports (id INT PRIMARY KEY AUTO_INCREMENT, plugin varchar(25),  name varchar(50), UUID varchar(50), report varchar(250), stamp TIMESTAMP, status varchar(350));");
            this.table.createTable(sql,"CREATE TABLE report_bans (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name varchar(25), UUID varchar(45), banned_by varchar(35), ban_reason varchar(250), stamp TIMESTAMP); ");

            if(log) {
                logToConsole("Table created.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SQL getAccess(){
        return sql;
    }
    public void connectionExists(){
        sql = new SQL("107.170.21.151","Logger","REQUEST1", "Logs");

    }

    private String message = ChatColor.translateAlternateColorCodes('&',"&a&oGathering Data...");

    public String getMessage() {
        return message;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {



        if(!(sender instanceof Player)) {
            sender.sendMessage("Cannot be used from console.");
            return true;
        }

        runConnection();



        Player p = (Player)sender;

        String name = p.getName();
        String uuid = ""+p.getUniqueId();
        String plugin = "Controller";

        ReportBans bans = new ReportBans();




        if(cmd.getName().equals("bugreport")) {

            bans.checkBanned(sql,p);
            if(bans.getStatus() == true) {

                bans.getReason(sql,p);

                //p.sendMessage(ChatColor.RED +"You have been banned from submitting reports.");
            }else {
                if(args.length < 1) {

                    p.sendMessage("Command usage: /bugreport <message>");
                }else if(args.length > 0) {

                    p.sendMessage(getMessage());
                    StringBuilder str = new StringBuilder();

                    for(int j = 0; j <args.length; j++) {
                        str.append(args[j] + " ");
                    }

                    String msg = str.toString();

                    msg = msg.replace("&", "§");

                    try {
                        PreparedStatement statement = this.sql.getConnection().prepareStatement("INSERT INTO user_reports (plugin,name,UUID,report,stamp) VALUES (?,?,?,?,?)");
                        statement.setString(1,plugin);
                        statement.setString(2,name);
                        statement.setString(3,uuid);
                        statement.setString(4,msg);
                        statement.setString(5,""+stamp.getStamp());

                        statement.execute();
                        statement.close();

                        //p.sendMessage(ChatColor.GREEN+"Your report was filed! Thanks for your help - The Dev's.");
                        p.sendMessage(ChatColor.GREEN+"Your report was filed! Thanks, " +p.getName() + ChatColor.GREEN +".");

                    }catch (SQLException e) {
                        e.printStackTrace();
                        p.sendMessage(ChatColor.RED+"Error - Report couldn't be filed!");
                    }
                    return true;
                }
            }
        }
        return true;
    }
}


/**
 *
 private String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
 private String[] authList = new String[10];
 public String getAuthor(){
 authList[0] = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
 return author;
 }
 */