package controller.SQL.SQLCOMMANDS.Report;

import controller.SQL.SQL;
import controller.SQL.SQLCOMMANDS.Report.Report;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ES359 on 3/11/15.
 */
public class GetReports implements CommandExecutor {

    public void checkReports(SQL con, Player p) {
        try {
            Statement s = con.c.createStatement();

            String query = "SELECT * FROM user_reports WHERE UUID='"+p.getUniqueId()+"'";

            ResultSet set = s.executeQuery(query);
            String header = ChatColor.GRAY +"Name           report    Status:\n--------------------------" ;
            p.sendMessage(report.getMessage());
            p.sendMessage(header);

            while(set.next()){

                String result = ChatColor.AQUA+set.getString(3) +ChatColor.RESET+"  "+ set.getString(5) +"  :"+ ChatColor.GOLD +"  " + set.getString(7);
                p.sendMessage(result);
            }
            p.sendMessage("\n");
            p.sendMessage(ChatColor.GRAY + "Data displayed.");
        }catch (SQLException e) {
            e.printStackTrace();
            p.sendMessage(ChatColor.RED+"ERROR - No data found.");
        }
    }

    public SQL sql;
    public Report report = new Report();
    private void connection() {

        report.connectionExists();
       this.sql = report.getAccess();

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        if(!(sender instanceof Player)){
            sender.sendMessage("This cannot be sent from the console.");
            return true;
        }

        connection();

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("reports")){
            if(args.length < 1) {
                checkReports(sql, p);
            }
        }
        return true;
    }
}
