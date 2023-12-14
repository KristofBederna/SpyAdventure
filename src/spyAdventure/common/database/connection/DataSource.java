package spyAdventure.common.database.connection;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection dbConnection;

    private static final String db_url = "jdbc:sqlite:SpyAdventure.sqlite";

    private DataSource() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.dbConnection = DriverManager.getConnection(db_url);
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public static DataSource getInstance() {
        return DataSourceInstanceHandler.INSTANCE;
    }

    private static class DataSourceInstanceHandler {
        private static final DataSource INSTANCE;

        static {
            try {
                INSTANCE = new DataSource();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
