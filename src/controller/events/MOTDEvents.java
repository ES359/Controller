package controller.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.enjin.es359.SettingsManager;
import org.bukkit.plugin.Plugin;

public class MOTDEvents implements Listener{

	SettingsManager sm = SettingsManager.getControllerInstance();
	
	@EventHandler
	public void ServerPing(ServerListPingEvent event) {
		String motd = sm.getConfig().getString("motd.server");
		motd = motd.replaceAll("&", "\u00A7");
		event.setMotd(motd);
    }


    public void setPingMotd(ServerListPingEvent event, Plugin plugin)
    {
        String motd = plugin.getConfig().getString("motd.ping");
        motd= motd.replaceAll("&","\u00A7");
        event.setMotd(motd);
    }

    public void setPingMotd(ServerListPingEvent event, String motd)
    {
       // String motd = plugin.getConfig().getString("motd.ping");
        motd= motd.replaceAll("&","\u00A7");
        event.setMotd(motd);
    }

}
