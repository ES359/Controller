package controller.SQL;

import com.enjin.es359.Controller;
import com.enjin.es359.Inform;
import com.enjin.es359.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ES359 on 10/27/14.
 */
public class SQLCommands extends Inform implements Listener{

    private Controller main;

    Timestamp ts = new Timestamp();

    public SQLCommands(Controller instance) {
        main = instance;
    }

    @EventHandler
    public void oncmd(PlayerCommandPreprocessEvent event) {


        if(main.getEnabled()) {
            Player p = event.getPlayer();
            String msg = event.getMessage();
            String stamp = ""+ts.getStamp();

            try {
                PreparedStatement statement = main.sql.c.prepareStatement("INSERT INTO commands (uuid, name, command, stamp) VALUES (?, ?, ?, ? );");
                statement.setString(1,""+p.getUniqueId());
                statement.setString(2,p.getName());
                statement.setString(3,msg);
                statement.setString(4, stamp);
                statement.execute();
                statement.close();

                Bukkit.getServer().getConsoleSender().sendMessage("Logged the command for the Player, " +p.getName());

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
