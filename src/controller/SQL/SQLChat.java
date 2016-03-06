package controller.SQL;

import com.enjin.es359.Controller;
import com.enjin.es359.Inform;
import com.enjin.es359.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by ES359 on 10/21/14.
 */
public class SQLChat extends Inform implements Listener{

   private Controller main;

    Timestamp ts = new Timestamp();

    public SQLChat(Controller instance) {
        main = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {


        if(main.getEnabled()) {
            Player p = event.getPlayer();

            String msg = event.getMessage();



            // f.logPlayerChat(p,event.getMessage(), "INSERT INTO chat (name, UUID, chat, stamp) VALUES ('" + p.getName()+" ', '" + p.getUniqueId()+ "', '"+ event.getMessage()+ "', '" +ts.getStamp()+ "' );");

            try {
                PreparedStatement statement = main.sql.c.prepareStatement("INSERT INTO chat (name, UUID, world, chat_msg, stamp) VALUES (?, ?, ?, ?, ?);");

                statement.setString(1,p.getName());
                statement.setString(2,p.getUniqueId().toString());
                statement.setString(3, p.getWorld().getName());
                statement.setString(4, event.getMessage());
                statement.setString(5,ts.getStamp().toString());
                statement.execute();
                statement.close();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GRAY +"Logged the chat for the Player, " +p.getName());

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}