package controller.events;

import com.enjin.es359.Controller;
import com.enjin.es359.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by ES359 on 3/7/15.
 */
public class AuthorEvent implements Listener {


    private String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    private String[] authList = new String[10];
    public String getAuthor(){
        authList[0] = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
        return author;
    }


    private Controller main;
    public AuthorEvent(Controller instance){
        this.main= instance;
    }


    SettingsManager sm = SettingsManager.getControllerInstance();




    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        String uuid =""+ p.getUniqueId();

        if(uuid.equals(author)) {
            p.sendMessage(ChatColor.GRAY+"User Authentication complete. - ");
        }
    }
}


/**
 *
 * if(!uuid.equals(sm.getAuthor())) {
 p.sendMessage("Unknown command. Type \"/help\" for help.");
 // p.sendMessage(ChatColor.YELLOW+"Uh oh... Doesn't look like this is yours  - " + p.getName());
 }else {
 *
 */

