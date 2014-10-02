package com.enjin.es359;

import org.bukkit.ChatColor;

public class Info {
	
	static public String prefix_Permission = ChatColor.translateAlternateColorCodes('&', "&9&oPermission &b&l> ");
	static public String prefix_Vote = ChatColor.translateAlternateColorCodes('&', "&a&oVote &b&l> ");
	static public String prefix_error = ChatColor.translateAlternateColorCodes('&', "&4&oError &b&l> ");
	static public String prefix_Console = ChatColor.translateAlternateColorCodes('&', "&d&oConsole &b&l> ");
	static public String prefix_argsError = ChatColor.translateAlternateColorCodes('&', "&c&oArguments &b&l> ");
	static public String prefix_Plugin = ChatColor.translateAlternateColorCodes('&', "&8&oController &a&l> ");
	

	public String permissionError() {
		return ChatColor.translateAlternateColorCodes('&', "&7You don't have permission for this.");
	}
	
	public String commandError() {
		return ChatColor.translateAlternateColorCodes('&', "&6&oThe requested command function couldn't be completed&a.");
	}
	
	public String chatInformEnabled() {

        return ChatColor.translateAlternateColorCodes('&', Info.prefix_Plugin+"&b&oGlobal &2Chat &6has been &a&o&lEnabled!");
    }
    public String chatinformDisabled() {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&b&oGlobal &2Chat &chas been &c&o&lDisabled!");
    }

    public String ConsoleEnabled() {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +" &6&oIs now &a&o&nEnabled&b.");
    }
    public String ConsoleDisabled() {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cIs now &6&n&oDisabled&4.");
    }
	
	public String returnArgumentError()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_error +"&cSorry you haven't entered a Playername.");
    }

	public String playerNotFound()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_error +"&cCouldn't find that player online ? &6&lAre they on the server ?");
    }

    public String returnRestriction()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cReleased the target &c&oPlayer, &a");
    }

    public String returnRestrictionFalseToPlayer()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cYou have been released by an Administrator.");
    }
    public String returnRestrictionTrueToPlayer()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cYou have been restricted by an Administrator.");
    }

    public String returnRestrictionTrueAdmin()
    {
        return ChatColor.translateAlternateColorCodes('&',Info.prefix_Plugin +"&cRestricted the target Player, &e&o");
    }

}
