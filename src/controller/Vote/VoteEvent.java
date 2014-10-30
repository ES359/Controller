package controller.Vote;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ES359 on 10/28/14.
 */
public class VoteEvent extends Inform implements Listener{


    SettingsManager sm = SettingsManager.getControllerInstance();

    private boolean voteEnabled = sm.getConfig().getBoolean("Voting.Enabled");
    private String voteBroadcast = sm.getConfig().getString("Voting.broadcast");

    public boolean isVoteEnabled() {
        return voteEnabled;
    }



    @EventHandler
    public void onVote(VotifierEvent event) {

        if(voteEnabled) {
            Vote v = event.getVote();
            String address = event.getVote().getAddress();
            String serviceName = v.getServiceName();
            String timeStamp = v.getTimeStamp();
            Player p = Bukkit.getServer().getPlayer(v.getUsername());

            voteBroadcast = voteBroadcast.replaceAll("%playername%",v.getUsername());
            voteBroadcast = voteBroadcast.replaceAll("%servicename%",v.getServiceName());

            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', voteBroadcast));

            if(p == null) {
                return;
            }

            p.getInventory().addItem(new ItemStack(Material.COOKIE,3));
            p.sendMessage(ChatColor.AQUA + "You have voted, " + ChatColor.GOLD + p.getName());

            logToConsole("&6The player, &b" + p.getName() + " &6has voted.");
        }
    }
}

/**
 String functions = sm.getConfig().getString("vote-broadcast");

 Vote v = event.getVote();
 functions = functions.replace("%playername%", v.getUsername());

 functions = functions.replace("%servicename%", v.getServiceName());

 Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', sm.getConfig().getString("prefix") + functions));


 Player p = Bukkit.getServer().getPlayer(event.getVote().getUsername());

 boolean giveItem = sm.getConfig().getBoolean("rewards.enabled");
 boolean warn = sm.getConfig().getBoolean("rewards.msg");


 */