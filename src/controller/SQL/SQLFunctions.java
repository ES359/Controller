package controller.SQL;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;

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

    public SQL sql;


    SettingsManager sm = SettingsManager.getControllerInstance();
    private boolean sqlEnabled = sm.getConfig().getBoolean("Database.Enabled");






    /**
     *  PreparedStatement ps = sql.c.prepareStatement("CREATE table users (name varchar(50), IP varchar(50) );");
     ps.executeUpdate();
     ps.close();
     Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED +"SQL Table 'users' created!");
     System.out.println("");

     */

}
