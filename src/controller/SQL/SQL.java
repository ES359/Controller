package controller.SQL;


import com.enjin.es359.Inform;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ES359 on 10/20/14.
 */
public class SQL extends Inform{

    public SQL() {}

     public Connection c;

    public SQL(String ip, String userName, String access, String db) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + access);
            logToConsole("&aConnection was &c&osuccessful!");
        } catch (Exception e) {
            e.printStackTrace();
            logToConsole(connectionErrorInform());
        }
    }

   public Connection getConnection() {
        return c;
    }




}
