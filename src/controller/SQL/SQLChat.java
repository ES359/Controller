package controller.SQL;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by ES359 on 10/21/14.
 */
public class SQLChat extends Inform{

    public SQLChat() {}

    SettingsManager sm = SettingsManager.getControllerInstance();
    public boolean logChat = sm.getConfig().getBoolean("");
    SQLFunctions sf =SQLFunctions.getInstance();



    static SQLChat instance = new SQLChat();


    static public SQLChat getInstance() {
        return instance;
    }



    public void logPlayerChat(Player p, String sql){

        String name = p.getName();
        String ip = "" +p.getAddress();
        UUID uuid = p.getUniqueId();

        sql = sql.replaceAll("%name%", p.getName());
        sql = sql.replaceAll("%ip%",""+ p.getAddress());
        sql = sql.replaceAll("%uuid%", "" +p.getUniqueId());

        try {
            sf.sql.c.prepareStatement(sql).executeUpdate();
            LogToConsole(sql_prefix +" &7Logged the chat for &bplayer, &8"+name);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getLogChat() {
        return logChat;
    }

    public void setLogChat(boolean val) {

    }

}