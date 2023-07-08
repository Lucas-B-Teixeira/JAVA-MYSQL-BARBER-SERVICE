package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection(){
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/barbearia";
        String user = "lucas";
        String password = "Teste123";

        try {
            connection = DriverManager.getConnection(url, user, password);
            //System.out.println("Conectado!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
