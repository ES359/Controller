package controller.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import com.enjin.es359.SettingsManager;

public class BlockListener implements Listener{



	SettingsManager br = SettingsManager.getControllerInstance();
	

	@EventHandler
public void onExplosionPrime(ExplosionPrimeEvent event) {
if(event.getEntity() instanceof TNTPrimed) {
	
	//Player p = (Player) event.getEntity();
	
	if(br.getConfig().getBoolean("Explosion.Disabled")) {
		event.setCancelled(true);
	}
  }
}
//


@EventHandler
public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {

//String permPlaceDenied = ChatColor.RED+""+ChatColor.BOLD+"["+ChatColor.GOLD+"BCCPBR"+ChatColor.RED+""+ChatColor.BOLD+"]"+ChatColor.RESET+" ";

 //String  prefix = br.getConfig().getString(ChatColor.translateAlternateColorCodes('&', "message-prefix"));
	


Player player = event.getPlayer();

String placeConfig = br.getConfig().getString("Bucket.WaterBucket.message");

placeConfig = placeConfig.replace("%player%", player.getName());

if(!player.hasPermission("Controller.event.restriction.water") && event.getBucket() == Material.WATER_BUCKET) {
	event.setCancelled(true);
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', placeConfig) + " " +ChatColor.DARK_RED +""+event.getBucket());
}

else if (player.hasPermission("Controller.event.restriction.water") && event.getBucket() == Material.WATER_BUCKET) {
    //Allowed
}


if(!player.hasPermission("Controller.event.restriction.lava") && event.getBucket() == Material.LAVA_BUCKET) {
	event.setCancelled(true);
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', placeConfig) + " " +ChatColor.DARK_RED +""+event.getBucket());
}
 else if (player.hasPermission("Controller.event.restriction.lava") && event.getBucket() == Material.LAVA_BUCKET) {
    //allowed
}
}



//=================
@SuppressWarnings("deprecation")
@EventHandler
public void onBlockPlace(BlockPlaceEvent event) {
//BlockPlace Event from BCCPBR (BLPR).


//String permPlaceDenied = ChatColor.RED+""+ChatColor.BOLD+"["+ChatColor.GOLD+"BCCPBR"+ChatColor.RED+""+ChatColor.BOLD+"]"+ChatColor.RESET+" ";

// String  prefix = br.getConfig().getString(ChatColor.translateAlternateColorCodes('&', "message-prefix"));
	


Player player = event.getPlayer();


String config;

config = br.getConfig().getString("Blocks.message");

config = config.replace("%player%", player.getName());




	if(!player.hasPermission("Controller.event.restrictionbypass") && br.getConfig().getIntegerList("Blocks.Restricted").contains(event.getBlock().getTypeId())) {
	event.setCancelled(true);
	player.sendMessage("" +ChatColor.translateAlternateColorCodes('&', config) + ""+ ChatColor.DARK_RED +" "+event.getBlock().getType());
	}

	else if (br.getConfig().getIntegerList("Blocks.Restricted").contains(event.getBlock().getTypeId())) {
	event.setCancelled(false);
	}
}
//========================================================


}

	

