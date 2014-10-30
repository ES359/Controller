package com.enjin.es359;

import controller.SQL.*;
import controller.Vote.VoteEvent;
import controller.commands.*;
import controller.events.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Controller extends JavaPlugin{



    Inform i = new Inform();
	SettingsManager sm = SettingsManager.getControllerInstance();
    private boolean enabled =getConfig().getBoolean("Database.Enabled");

    private CPMenuEvent cpm;
	private UserCPCommand ucp;
    private ChatCommand cc;

    public SQL sql;

    private CreateSQLTables f;


    public boolean getEnabled() {
        return enabled;
    }

    /**
     * http://zetcode.com/db/mysqljava/
     *
     * http://blog.lolmewn.nl/
     *
     * https://floobits.com/
     *
     * http://alvinalexander.com/java/java-timestamp-example-current-time-now
     */



    public void onEnable() {

        /**
         * SQL system.
         *
         * Added boolean checking system.
         */

        if(enabled) {
            sql = new SQL(getConfig().getString("Database.host"), getConfig().getString("Database.username"), getConfig().getString("Database.password"), getConfig().getString("Database.database"));
            f = new CreateSQLTables();
            f.createTable(sql, getConfig().getBoolean("Table.logchat"), "CREATE TABLE IF NOT EXISTS chat (name varchar(50), UUID VARCHAR(50), World varchar(20), chat varchar(150), stamp varchar(50) );");
            f.createTable(sql,getConfig().getBoolean("Table.logcommands"), "CREATE TABLE IF NOT EXISTS commands (name varchar(50), UUID varchar(50), command varchar(150), stamp varchar(50) );");
            f.createTable(sql,getConfig().getBoolean("Table.logplayer"), "CREATE TABLE IF NOT EXISTS playertable (uuid VARCHAR(50), name VARCHAR(50), ip varchar(35), exp VARCHAR(50), world VARCHAR(25), location varchar(60), isOp varchar(10), whitelist varchar(10), stamp varchar(50) );");
        }

        if(enabled == false ){
            i.logToConsole("&cSQL is disabled in &a&oconfiguration file!");
        }


       i.logToConsole(i.ConsoleEnabled());
       // Bukkit.getServer().getConsoleSender().sendMessage(i.ConsoleEnabled());
		
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
        pm.registerEvents(new SQLChat(this), this);
        pm.registerEvents(new SQLCommands(this), this);
        pm.registerEvents(new SQLJoin(this), this);
        pm.registerEvents(new VoteEvent(), this);
		registerAllCommands();
	}


	public void onDisable() {	



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
    /*
    public SQL getSQL() {
        return sql;
    }
    */

}
