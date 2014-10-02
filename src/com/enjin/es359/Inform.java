package com.enjin.es359;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inform {

    public String ConsoleError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &6This command isn't for use by the &c[&2Console&c].");
	}
	
	public String ArgumentsError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &6You have used incorrect arguments.");
	}
	
	public String permissionError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &l&eYou don't have permissions for this.");
	}
	
	public String functionError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &cCould not complete the requested function.");
	}
	
	public String tp()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cTeleporting to the &ePlayer, &6");
	}
	
	public String tphere()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7You have been teleported...");
	}
	public String tphere2()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cTeleporting, &e");
	}
	
	public String kill()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7You have been killed.");
	}
	public String kill2()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cYou killed the player &6, ");
	}
	
	public String playerCR()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7Your gamemode was changed to &bCreative&7...");
	}
	public String adminCR()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cYou changed the Gamemode to &bCreative &cfor, &e ");
	}
	
	public String playerSU()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7Your gamemode was changed to &2Survival&7...");
	}
	public String adminSU()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cYou changed the Gamemode to &2Survival &cfor, &e ");
	}
	
	public String playerAD()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7Your gamemode was changed to &6Adventure&7...");
	}
	public String adminAD()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cYou changed the Gamemode to &6Adeventure &cfor, &e ");
	}
	public String playerHeal()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &7You have been &bhealed.");
	}
	public String adminHeal()
	{
		return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+" &cYou &ahealed the player &6, ");
	}

    public String connectionError() {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin+"&c&lConnection Error, could not establish a &c&oconnection.");
    }

    /**
   static public String informPlayer(String msg, Player player) {

        msg = msg.replaceAll("player", player.getName());



        return ChatColor.translateAlternateColorCodes('&',msg);
    }
     */

    static public void informPlayer(String msg, Player val) {

        msg = msg.replaceAll("[playername]",val.getName());
        msg = msg.replaceAll("[displayname]",val.getDisplayName());
        msg = msg.replaceAll("[playerlistname]",val.getPlayerListName());
        msg = msg.replaceAll("[worldname]",val.getWorld().getName());
        msg = msg.replaceAll("[IP]","" +val.getAddress());
        val.sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
    }


    static public void informPlayer(String msg, CommandSender s) {


    }

}
