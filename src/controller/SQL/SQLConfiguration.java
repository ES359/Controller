package controller.SQL;

import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;

/**
 * Created by ES359 on 10/21/14.
 */
public class SQLConfiguration extends Inform {

    /**
     * By default we will have the SQL settings set to false.
     * That means we need to encapsualate all of the try/Catch statements with a boolean value.
     */

    public SQLConfiguration() {}

    public static SQLConfiguration instance = new SQLConfiguration();

    static public SQLConfiguration getConfigurationInstance() {
        return instance;
    }

    SettingsManager sm = SettingsManager.getControllerInstance();




    private boolean sqlEnabled = sm.getConfig().getBoolean("Database.Enabled");
    public String host = sm.getConfig().getString("Database.host");
    public String username = sm.getConfig().getString("Database.username");
    public String password = sm.getConfig().getString("Database.password");
    public String database = sm.getConfig().getString("Database.database");
    public boolean tablechat = sm.getConfig().getBoolean("Table.chat");
    public boolean tableplayer = sm.getConfig().getBoolean("Table.playerdata");
    public boolean tableCmds = sm.getConfig().getBoolean("Table.commands");


    /**
     * SQL enabled Getter method.
     *
     * Will return status of the variable.
     *
     */
    public boolean getSqlEnabled() {
        return sqlEnabled;
    }


public boolean getTableChat() {
    return tablechat;
}
    public boolean getTablePlayer() {
        return tableplayer;
    }

    public boolean getTableCmds() {
        return tableCmds;
    }


}

