package com.example.bdsproject.config;

import com.example.bdsproject.DatabaseConfig;
import javafx.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseQuery.class);

    public static void insertQuery (ActionEvent event, String query) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try (Connection connection = DatabaseConfig.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Cannot execute the query.", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteQuery(ActionEvent event, String table, int id) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM public." + table + " WHERE " + table + "_id = " + id + ";";

        try (Connection connection = DatabaseConfig.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Cannot execute the query.", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateQuery(ActionEvent event, String table, int id, String column, String value) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE public." + table + " SET " + column + " = " + value + " WHERE " + table + "_id = " + id + ";";

        try (Connection connection = DatabaseConfig.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Cannot execute the query.", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
