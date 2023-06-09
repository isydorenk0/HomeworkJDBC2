package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory implements IDAOFactory {
    private static DAOFactory instance;
    private static Connection connection;
    private DAOFactory() {
        try {
            // Class.forName("com.mysql.cj.jdbc.driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public synchronized static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
