/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:42 PM
 * Project Name:texnopark-information-bot
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static Connection connection;

    static {
        try {
            connection = DbConfig.connect("nukus-texnopark-info-db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        String username = "postgres";
        String password = "25052001Dm.";

        return DriverManager.getConnection(url,username,password);
    }
}
