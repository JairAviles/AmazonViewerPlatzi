package mx.jairaviles.amazonviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import static  mx.jairaviles.amazonviewer.db.DataBase.*;

public interface IDBConnection {

    default Connection connectDB() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DB_NAME, DB_USER, DB_PASSWORD);

            if (connection != null) {
                System.out.println("Se establecio la conexion :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

}
