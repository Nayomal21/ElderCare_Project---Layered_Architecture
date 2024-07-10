package lk.ijse.model;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private  static  DbConnection dbConnection;
    private Connection connection;
    private DbConnection() {

        try {
             connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ElderCare",
                    "root",
                    "Ijse@123"
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public static DbConnection getInstance() {

        if (dbConnection== null)
            dbConnection =new DbConnection();


        return dbConnection;

    } public Connection getConnection() {
        return connection;
    }

}
