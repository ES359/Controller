package controller.SQL;

import com.enjin.es359.Controller;
import com.enjin.es359.Inform;
import com.enjin.es359.SettingsManager;

import java.sql.SQLException;

/**
 * Created by ES359 on 10/21/14.
 */

/**
 * Table creation Methods and logic.
 */


public class CreateSQLTables extends Inform {

    SettingsManager sm = SettingsManager.getControllerInstance();

    SQLConfiguration sconfig = SQLConfiguration.getConfigurationInstance();
    public SQLConnection sql;
    //Controller con = Controller.getMainInstance();



    public void createTables() {
        if(sconfig.getSqlEnabled()) {

            sql = new SQLConnection(sm.getConfig().getString("Database.host"), sm.getConfig().getString("Database.username"), sm.getConfig().getString("Database.password"), sm.getConfig().getString("Database.database"));

            try {
                sql.c.prepareStatement("CREATE TABLE IF NOT EXISTS commands (uuid VARCHAR(50), name VARCHAR(50), command VARCHAR(150)); ").executeUpdate();

            }catch (SQLException e ) {
                e.printStackTrace();
            }

            try {
                sql.c.prepareStatement("CREATE TABLE IF NOT EXISTS chat (name varchar(50), UUID VARCHAR(50), chat varchar(150) ); ").executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                sql.c.prepareStatement("CREATE TABLE IF NOT EXISTS playertable (uuid VARCHAR(50), name VARCHAR(50), ip varchar(35), exp VARCHAR(50), world VARCHAR(25), location varchar(60), isOp varchar(10), whitelist varchar(10) );").executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
