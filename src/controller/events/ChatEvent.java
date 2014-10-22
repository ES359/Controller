package controller.events;

import com.enjin.es359.SettingsManager;
import controller.SQL.SQLChat;
import controller.SQL.SQLFunctions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by ES359 on 9/27/14.
 */
public class ChatEvent implements Listener {

    public ChatEvent() {}

    static ChatEvent chatInstance = new ChatEvent();

    public static ChatEvent getChatInstance() {
        return chatInstance;
    }

    SettingsManager sm = SettingsManager.getControllerInstance();

    SQLChat chat = SQLChat.getInstance();

    public  boolean enabled = sm.getConfig().getBoolean("custom-chat.Enabled");

    public boolean returnValue() {
        return enabled;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();



        if(enabled) {

            /**
             * Add more features.
             */

            String name = sm.getConfig().getString("custom-chat.Format");
                    name = name.replaceAll("%username%", player.getName());
                    name = name.replaceAll("%message%", event.getMessage());
                    name = name.replaceAll("%world%", player.getWorld().getName());
                    name = name.replaceAll("%UUID%", "" + player.getUniqueId());
                    name = name.replaceAll("%IP%", "" +player.getAddress());

                   event.setFormat(ChatColor.translateAlternateColorCodes('&',name));
        }

        if(chat.logChat) {
            chat.logPlayerChat(player, "");
        }



    }

    public boolean chatEnabled;

    public boolean returnChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(boolean val) {
        chatEnabled = val;
    }

    @EventHandler
    public void chatEnable(AsyncPlayerChatEvent event) {

        Player pl = event.getPlayer();

        if(returnChatEnabled()) {
            if(!pl.hasPermission("Controller.event.chatbypass")) {
                event.setCancelled(true);

                String conf = sm.getConfig().getString("chat-disabled.msg");
                conf = conf.replaceAll("%playername%", pl.getName());

                pl.sendMessage(ChatColor.translateAlternateColorCodes('&',conf));
            }else {

                event.setCancelled(false);
            }
        }

    }


}
