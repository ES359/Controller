package controller.SQL;
import com.enjin.es359.Inform;
import java.sql.SQLException;

/**
 * Created by ES359 on 10/21/14.
 */


public class CreateSQLTables extends Inform {

    /**
     *
     * @param val Takes a SQL parameter.
     * @param instance Checks to see if the table should be created via a config.
     * @param sql Parameters you wish to define for table.
     */
    public void createTable(SQL val, boolean instance, String sql) {
        if(instance) {
            try{
                  val.c.prepareStatement(sql).executeUpdate();
                logToConsole("&bSQL table has been created!");
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
