package controller.events;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

/**
 * Created by ES359 on 9/29/14.
 */
public class RestrictedCommands implements Listener {

    SettingsManager sm = SettingsManager.getControllerInstance();

    Info i = new Info();

    @EventHandler
    public void commandProcess(PlayerCommandPreprocessEvent event) {

        Player p = event.getPlayer();

        List<String> blockedcmds = sm.getConfig().getStringList("restricted-cmds");


        String msg = event.getMessage();

        for(int i=0; i <blockedcmds.size(); i++) {

        }

    }
}
