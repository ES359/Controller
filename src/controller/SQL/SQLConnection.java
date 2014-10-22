package controller.SQL;

import com.enjin.es359.Inform;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ES359 on 10/20/14.
 */
public class SQLConnection {
    Inform i = new Inform();

   static public Connection c;

    public SQLConnection(String ip, String userName, String access, String db) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Connection Error.");
        }
    }

   static public Connection getConnection() {
        return c;
    }




}
