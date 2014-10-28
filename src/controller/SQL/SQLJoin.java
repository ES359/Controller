package controller.SQL;

import com.enjin.es359.Controller;
import com.enjin.es359.Inform;
import com.enjin.es359.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ES359 on 10/27/14.
 */
public class SQLJoin extends Inform implements Listener{


    Timestamp ts = new Timestamp();

    private Controller main;

    public SQLJoin(Controller instance) {
        main = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {


        if(main.getEnabled() == false) {
            event.getPlayer().sendMessage(SQLIsDisabled());
        }


        if(main.getEnabled()) {
            Player p = event.getPlayer();

            String location = "X: " +p.getLocation().getBlockX() + " Y: " + p.getLocation().getY() + " Z: " + p.getLocation().getBlockZ();

            try {
                PreparedStatement statement = main.sql.c.prepareStatement("INSERT INTO playertable (uuid, name, ip, exp, world, location, isOp, whitelist, stamp) VALUES (?,?,?,?,?,?,?,?,?);");

                statement.setString(1,""+p.getUniqueId());
                statement.setString(2,p.getName());
                statement.setString(3,""+p.getAddress());
                statement.setString(4, ""+p.getExpToLevel());
                statement.setString(5, ""+p.getWorld().getName());
                statement.setString(6,location);
                statement.setString(7, ""+p.isOp());
                statement.setString(8, ""+p.isWhitelisted());
                statement.setString(9, ""+ts.getStamp());
                statement.execute();
                statement.close();

                Bukkit.getServer().getConsoleSender().sendMessage("Logged the player, " + p.getName());

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
