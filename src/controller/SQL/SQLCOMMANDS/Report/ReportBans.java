package controller.SQL.SQLCOMMANDS.Report;

import controller.SQL.CreateSQLTables;
import controller.SQL.SQL;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ES359 on 4/5/15.
 */
public class ReportBans {

    private SQL sql;
    private Report report;

    private String user_name;
    private String user;
    private boolean banned;

    public String getUser() {
        return this.user_name;
    }

    public String getUserUUID(){
        return this.user;
    }

    public boolean getBanned() {
        return this.banned;
    }

    public CreateSQLTables table = new CreateSQLTables();

    public void connection() {
        report.connectionExists();
        this.sql = report.getAccess();
        this.table.createTable(sql,"create table report_bans (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name varchar(25), UUID varchar(45), ban_status boolean, ban_reason varchar(100)); ");

    }

    public void checkBanned(SQL sql, Player p)  {



        String name = p.getName();
        String uuid = ""+p.getUniqueId();
        String status;

        try {
            Statement s = sql.c.createStatement();

            String query = "SELECT * FROM report_bans WHERE UUID='"+uuid+"'";

            ResultSet set = s.executeQuery(query);
            while(set.next()) {
                String result = set.getString(3);
                if(result.equals(uuid))
                {
                    this.setStatus(true);
                }else {
                    this.setStatus(false);
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public void getReason(SQL sql, Player p) {
        String uuid = ""+p.getUniqueId();

        try{
            Statement s = sql.getConnection().createStatement();

            String query = "SELECT ban_reason FROM report_bans WHERE UUID='"+uuid+"'";

            ResultSet set = s.executeQuery(query);

            while(set.next()) {
                //  p.sendMessage(report.getMessage());
                String result = ChatColor.RED + set.getString(1);

                //this.reason = result;
                p.sendMessage(result);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean b) {
        this.status = b;
    }

    /**
     *
     * Possible that we might implement ingame access to developers.
     */
    @Deprecated
    public void submitBan(SQL sql, String uuid) {}


}
