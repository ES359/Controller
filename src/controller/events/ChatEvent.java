package controller.events;

import com.enjin.es359.Info;
import com.enjin.es359.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by ES359 on 9/27/14.
 */
public class ChatEvent implements Listener {

    SettingsManager sm = SettingsManager.getControllerInstance();

    Info i = new Info();
    public  boolean enabled = sm.getConfig().getBoolean("custom-chat.Enabled");

    public boolean returnValue() {
        return enabled;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();



        if(enabled) {

            String name = sm.getConfig().getString("custom-chat.Format");
                name = name.replaceAll("%username%", player.getName());

                  name = name.replaceAll("%message%", event.getMessage());

                    name = name.replaceAll("%world%", player.getWorld().getName());

                   event.setFormat(ChatColor.translateAlternateColorCodes('&',name));
        }//End of boolean expression.
    }
}
