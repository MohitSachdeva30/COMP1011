package org.lab1.assignment1;

import java.sql.*;

public class DBUtility {
    private static final String URL = "jdbc:mysql://localhost:3306/video_game_data";
    private static final String USER = "root";
    private static final String PASSWORD = "Karm#531";

    // Method to establish database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to retrieve all games from the database
    public static ResultSet getAllGames() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM games");
    }
}
