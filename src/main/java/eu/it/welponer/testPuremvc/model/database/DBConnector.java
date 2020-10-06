package eu.it.welponer.testPuremvc.model.database;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DBConnector {

    abstract Connection getConnection();
    abstract ResultSet executeQuery(String query); // for select from query
    abstract int updateQuery(String query); // for insert query
}
