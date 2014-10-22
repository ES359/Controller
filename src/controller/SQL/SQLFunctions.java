package controller.SQL;

import com.enjin.es359.Inform;
import controller.SQL.SQLConnection;
import com.enjin.es359.SettingsManager;
import org.bukkit.Bukkit;

import java.sql.SQLException;

/**
 * Created by ES359 on 10/20/14.
 */
public class SQLFunctions extends Inform{


    public SQLFunctions() {}

   public static SQLFunctions instance = new SQLFunctions();

   public  static SQLFunctions getInstance() {
        return instance;
    }

    public SQLConnection sql;


    SettingsManager sm = SettingsManager.getControllerInstance();
    private boolean sqlEnabled = sm.getConfig().getBoolean("Database.Enabled");


    public boolean returnEnabled() {
        return sqlEnabled;
    }



    public void InitiateConnection() {

        if(sqlEnabled) {
            sql = new SQLConnection(sm.getConfig().getString("Database.host"), sm.getConfig().getString("Database.host"), sm.getConfig().getString("Database.username"), sm.getConfig().getString("Database.password"));
            return;
        }
        if(returnEnabled() == false) {
            LogToConsole(SQLIsDisabled());
        }
    }

    public void createTables() {
        try {


            sql.c.prepareStatement("CREATE TABLE IF NOT EXISTS users (name varchar(50), UUID VARCHAR(50), IP varchar(50) );").executeUpdate();
            sql.c.prepareStatement("CREATE TABLE IF NOT EXISTS chat (name varchar(50), UUID VARCHAR(50), chat varchar(150) ); ").executeUpdate();
            System.out.println("Tables were CREATED.");
        }catch (SQLException e) {
             e.printStackTrace();
            LogToConsole(connectionErrorInform());
        }
    }



    /**
     *  PreparedStatement ps = sql.c.prepareStatement("CREATE table users (name varchar(50), IP varchar(50) );");
     ps.executeUpdate();
     ps.close();
     Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED +"SQL Table 'users' created!");
     System.out.println("");

     */

}
