/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj.housemgmt.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author didhitimaharjan
 */

public class ConnectionFactory {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "root";

    private static final String URL = "jdbc:mysql://localhost:3306/house_mgmt";

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
        Connection connection = ConnectionFactory.getConnection();
        System.out.println(connection);
    }
}


