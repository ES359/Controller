package controller.events;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

/**
 * Created by ES359 on 9/29/14.
   Contributed: http://forums.bukkit.org/members/ferusgrim.90872703/
 */
public class RestrictedCommands extends Inform implements Listener {

    SettingsManager sm = SettingsManager.getControllerInstance();

    @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent event) {

        String denied = sm.getConfig().getString("restriction-msg");

        denied = denied.replaceAll("%playername%", event.getPlayer().getName());




        String message[] = event.getMessage().split(" ");
            String command = message[0];
            List<String> denyList = sm.getConfig().getStringList("blocked-cmds");

            if (denyList.contains(command)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', denied));

            }


        /**

        List<String> c = sm.getConfig().getStringList("blocked-cmds");

        if(c.contains(event.getMessage().split(" ", 1))) {
            event.setCancelled(true);

        }else if(event.getPlayer().hasPermission("Controller.event.commandbypass") || event.getPlayer().isOp()) {
            event.setCancelled(false);
        }
    }

    /**
     * @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent event) {
    String message[] = event.getMessage().split(" ");
    String command = message[0];
    List<String> denyList = sm.getConfig().getStringList("blocked-cmds");

    if (denyList.contains(command)) {
    event.setCancelled(true);
    }
    }
     */
    }
}
