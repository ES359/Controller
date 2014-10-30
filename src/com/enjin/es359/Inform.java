package com.enjin.es359;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
public class Inform {

    /**
     * When using an interface, we can just declare them and use another class that implements them to set their variables...
     * Sounds like something we should do with this.
     */


    /**
     * Add a different type of permission denied message with player Information ETC.
     */

    static public final String prefix_Permission = ChatColor.translateAlternateColorCodes('&', "&9&oPermission &b&l> ");
    static public final String prefix_Vote = ChatColor.translateAlternateColorCodes('&', "&a&oVote &b&l> ");
    static public final String prefix_error = ChatColor.translateAlternateColorCodes('&', "&4&oError &b&l> ");
    static public final String prefix_Console = ChatColor.translateAlternateColorCodes('&', "&d&oConsole &b&l> ");
    static public final String prefix_argsError = ChatColor.translateAlternateColorCodes('&', "&c&oArguments &b&l> ");
    static public final String prefix_Plugin = ChatColor.translateAlternateColorCodes('&', "&a[&8&oCP&a] ");
    static public final String sql_prefix = ChatColor.translateAlternateColorCodes('&',"&8SQL&c>");

    public String permissionError() {
        return ChatColor.translateAlternateColorCodes('&', "&7You don't have permission for this.");
    }

    public String commandError() {
        return ChatColor.translateAlternateColorCodes('&', "&6&oThe requested command function couldn't be completed&a.");
    }

    public String chatInformEnabled() {

        return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+"&b&oGlobal &2Chat &6has been &a&o&lEnabled!");
    }
    public String chatinformDisabled() {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&b&oGlobal &2Chat &chas been &c&o&lDisabled!");
    }

    public String ConsoleEnabled() {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +" &6&oIs now &a&o&nEnabled&b.");
    }
    public String ConsoleDisabled() {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cIs now &6&n&oDisabled&4.");
    }




    public String returnArgumentError()
    {
        return ChatColor.translateAlternateColorCodes('&',prefix_error +"&cSorry you haven't entered a Playername.");
    }

    public String playerNotFound()
    {
        return ChatColor.translateAlternateColorCodes('&', prefix_error +"&cCouldn't find that player online ? &6&lAre they on the server ?");
    }

    public String returnRestriction()
    {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cReleased the target &c&oPlayer, &a");
    }

    public String returnRestrictionFalseToPlayer()
    {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cYou have been released by an Administrator.");
    }
    public String returnRestrictionTrueToPlayer()
    {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cYou have been restricted by an Administrator.");
    }

    public String returnRestrictionTrueAdmin()
    {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin +"&cRestricted the target Player, &e&o");
    }



    public String ConsoleError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &6This command isn't for use by the &c[&2Console&c].");
	}
	
	public String ArgumentsError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &6You have used incorrect arguments.");
	}

	public String functionError()
	{
		return ChatColor.translateAlternateColorCodes('&', "&4Error &l&7> &cCould not complete the requested function.");
	}
	
	public String tpInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cTeleporting to the &ePlayer, &6");
	}
	
	public String tphereInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7You have been teleported...");
	}
	public String tphere2Inform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cTeleporting, &e");
	}
	
	public String killInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7You have been killed.");
	}
	public String kill2Inform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cYou killed the player &6, ");
	}
	
	public String playerCRInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7Your gamemode was changed to &bCreative&7...");
	}
	public String adminCRInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cYou changed the Gamemode to &bCreative &cfor, &e ");
	}
	
	public String playerSUInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7Your gamemode was changed to &2Survival&7...");
	}
	public String adminSUInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cYou changed the Gamemode to &2Survival &cfor, &e ");
	}
	
	public String playerADInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7Your gamemode was changed to &6Adventure&7...");
	}
	public String adminADInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cYou changed the Gamemode to &6Adeventure &cfor, &e ");
	}
	public String playerHealInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &7You have been &bhealed.");
	}
	public String adminHealInform()
	{
		return ChatColor.translateAlternateColorCodes('&', prefix_Plugin+" &cYou &ahealed the player &6, ");
	}



    public String connectionErrorInform() {
        return ChatColor.translateAlternateColorCodes('&',prefix_Plugin+"&c&lConnection Error, could not establish a &c&oconnection.");
    }

    public String SQLIsDisabled() {
        return ChatColor.translateAlternateColorCodes('&', prefix_Plugin +"&4Warning... &c&oSQL is disabled by default in &aConfig.");
    }

    public void logToConsole(String l) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', l));
    }

    public void globalBroadcast(String broadcast) {
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));
    }


    /**
     * Add Configuration msg.
     */

    /**
   static public String informPlayer(String msg, Player player) {

        msg = msg.replaceAll("player", player.getName());



        return ChatColor.translateAlternateColorCodes('&',msg);
    }
     */

     /*



    static public String informPlayer(String msg , Player val) {

        msg = msg.replaceAll("&", "§");

        msg = msg.replaceAll("[playername]",val.getName());
        msg = msg.replaceAll("[displayname]",val.getDisplayName());
        msg = msg.replaceAll("[playerlistname]",val.getPlayerListName());
        msg = msg.replaceAll("[worldname]",val.getWorld().getName());
        msg = msg.replaceAll("[IP]","" +val.getAddress());

        return msg;
    }
    */
}
