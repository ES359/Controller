package controller.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.enjin.es359.SettingsManager;

public class MOTDEvents implements Listener{

	SettingsManager sm = SettingsManager.getControllerInstance();
	
	@EventHandler
	public void ServerPing(ServerListPingEvent event) {
		String motd = sm.getConfig().getString("motd.server");
		motd = motd.replaceAll("&", "\u00A7");
		event.setMotd(motd);
	}
	
}
