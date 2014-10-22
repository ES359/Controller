package com.enjin.es359;

import controller.SQL.SQLConnection;
import controller.commands.*;
import controller.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLData;

public class Controller extends JavaPlugin{

    public Controller() {}

    static public Controller instance = new Controller();

    static public Controller getMainInstance() {
        return instance;

    }

    Inform i = new Inform();
	
	SettingsManager sm = SettingsManager.getControllerInstance();

	public CPMenuEvent cpm;
	
	public UserCPCommand ucp;

    public ChatCommand cc;

    private SQLConnection sql;


    /**
     * SQL Configuration settings.
     */

    /*
    private boolean sqlEnabled = getConfig().getBoolean("Database.Enabled");
    private String host = getConfig().getString("Database.host");
    private String username = getConfig().getString("Database.username");
    private String password = getConfig().getString("Database.password");
    private String database = getConfig().getString("Database.database");
    */

    public void onEnable() {

        /**
         * Implemented SQL System.
         */

        /**
         *
         */



        Bukkit.getServer().getConsoleSender().sendMessage(i.ConsoleEnabled());
		
		sm.configSetup(this);
        ucp = new UserCPCommand(this);
		cpm = new CPMenuEvent(this, this);
        cc = new ChatCommand();


        PluginManager pm = Bukkit.getServer().getPluginManager();
		
		//████████
		
	//	pm.registerEvents(new UserCPCommand(this), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new MOTDEvents(), this);
		pm.registerEvents(new JoinQuitEvents(), this);
		pm.registerEvents(new WhitelistEvent(), this);
        pm.registerEvents(new ChatEvent(), this);
        pm.registerEvents(new PlayerRestrictEvent(), this);
        pm.registerEvents(new RestrictedCommands(), this);
		registerAllCommands();
	}


	public void onDisable() {	

        Bukkit.getServer().getConsoleSender().sendMessage(i.ConsoleDisabled());
	}
	

	public void registerAllCommands() {
		registerCmd("alert", new AlertCommand());
		registerCmd("motd", new MOTDCommand());
		registerCmd("setmotd", new SetMotdCommand());
		registerCmd("setlistmotd", new SetListMotdCommand());
		registerCmd("cp", new CPCommand(cpm ));
		registerCmd("kickmsg", new KickMsgCommand());
		registerCmd("setkickmsg", new SetkickMsgCommand());
		registerCmd("usercp", new UserCPCommand(this));
        registerCmd("chat", new ChatCommand());
        registerCmd("restrict", new RestrictCommand());
        registerCmd("permissions", new PermissionsCommand());
	}
	
	public void registerCmd(String command, CommandExecutor commandExecutor)
	{
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
	}

     public SQLConnection getSQL() {
        return sql;
    }

}
