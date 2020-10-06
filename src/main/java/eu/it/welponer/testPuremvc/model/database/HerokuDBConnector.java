package eu.it.welponer.testPuremvc.model.database;

import java.sql.Connection;
import java.sql.ResultSet;

/*
 * Singleton to connect to Heroku database
 */
public class HerokuDBConnector extends DBConnector {

    private static HerokuDBConnector instance;

    static{
        instance = new HerokuDBConnector();
    }

    public static HerokuDBConnector getInstance(){
        return instance;
    }

    @Override
    Connection getConnection() {
        return null;
    }

    @Override
    ResultSet executeQuery(String query) {
        return null;
    }

    @Override
    int updateQuery(String query) {
        return 0;
    }
}
