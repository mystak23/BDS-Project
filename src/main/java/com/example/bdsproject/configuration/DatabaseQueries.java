package com.example.bdsproject.configuration;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.*;

public class DatabaseQueries {

    public static void insertQuery (ActionEvent event, String query) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot insert such data!");
            alert.showAndWait();
            throwables.printStackTrace();
        } finally {
            DatabaseConfig.closeConnection();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("You have successfully inserted the data!");
            alert.showAndWait();
        }
    }

    public static void deleteQuery(ActionEvent event, String table, int id) {
        Connection connection = null;
        Statement statement = null;
        String query = "DELETE FROM " + table + " WHERE " + table + "_id = " + id;
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConfig.closeConnection();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateQuery(ActionEvent event, String table, int id, String column, String value) {
        Connection connection = null;
        Statement statement = null;
        String query = "UPDATE " + table + " SET " + column + " = " + value + " WHERE " + table + ".id = " + id;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot update such data!");
            alert.showAndWait();
            throwables.printStackTrace();
        } finally {
            DatabaseConfig.closeConnection();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
