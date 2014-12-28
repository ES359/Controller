package controller.events;

import com.enjin.es359.Inform;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 11/1/14.
 */
public class DisconnectPlayer extends Inform {

    public void disconnect(Player p) {

        if(!p.hasPermission("Controller.cp.events.disconnect")) {
            p.closeInventory();
            p.sendMessage(permissionError());
        }else {
            p.kickPlayer(disconnectMsg());
        }
    }
}
