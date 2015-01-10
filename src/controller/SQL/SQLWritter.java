package controller.SQL;



import java.sql.SQLException;

/**
 * Created by ES359 on 1/9/15.
 */
public class SQLWritter extends SQL{

    private String statement;

    public SQLWritter() {}

    public SQLWritter(String sql) {
        this.statement = sql;
    }

    public String getStatement(){
        return statement;
    }

    public void writeSQL(String sql) {

        try {
            getConnection().prepareStatement(sql).executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// 1/2 no salt turkey london broil blakfrest ham 2 skim  2 15B