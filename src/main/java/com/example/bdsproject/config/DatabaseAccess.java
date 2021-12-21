package com.example.bdsproject.config;

import com.example.bdsproject.DatabaseConfig;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAccess {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    public static void signUpUser(ActionEvent event, String username, String password) {
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try (Connection connection = DatabaseConfig.getConnection()){
            psCheckUserExists = connection.prepareStatement("SELECT * FROM public.user WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.showAndWait();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO public.user (username, psw) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, hashPassword(password));
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Successfully signed up to the database application!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error occured.");
            alert.showAndWait();
            logger.error("Cannot sign up.", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = DatabaseConfig.getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT psw FROM public.user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.showAndWait();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("psw"); //zahashovane
                    if (hashPassword(password).equals(retrievedPassword)) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Log in successful!");
                        alert.showAndWait();
                        DatabaseConfig.changeScene(event, "Menu.fxml");
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("You can view all cars we rent.");
                        alert2.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.showAndWait();
                    }
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error occured.");
            alert.showAndWait();
            logger.error("Cannot log in.", e);
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

    private static String hashPassword(String password) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

}
