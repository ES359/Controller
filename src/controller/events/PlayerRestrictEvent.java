package controller.events;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by ES359 on 9/23/14.
 */
public class PlayerRestrictEvent implements Listener {

    SettingsManager sm = SettingsManager.getControllerInstance();

    Info i = new Info();



    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();

        if(sm.restricted.contains(player.getName()))
        {
            event.setTo(event.getFrom());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', sm.getConfig().getString("restricted-msg")));
        }

    }

}
