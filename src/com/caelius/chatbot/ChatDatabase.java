package com.caelius.chatbot;

import java.net.URL;
import java.sql.*;
public class ChatDatabase {
	static Connection connection;
    public static void initializeDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/chatbot";
            String username = "root";
            String password = "Mansi2002";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String queryDatabase(String user_input) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT bot FROM chatdatabase WHERE you=?");
            preparedStatement.setString(1, user_input);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("bot");
            } else {
            	try {
                    URL url = new URL("https://google.com/search?q=" + user_input.replace(" ", "+"));
                    java.awt.Desktop.getDesktop().browse(url.toURI());
                    return "Maybe this will answer your question.";
                } catch (Exception e) {
                    return "Please connect to the internet for better results.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred while processing your request.";
        }
    }

}
