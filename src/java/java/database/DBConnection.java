/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sandeep
 */
public class DBConnection {

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://localhost/inventory";
            String user = "root";
            String pass = "";
            connection = DriverManager.getConnection(jdbc);
            connection = DriverManager.getConnection(user);
            connection = DriverManager.getConnection(pass);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Getting the exception here " + ex.getMessage());
        }
        return connection;

    }

}
