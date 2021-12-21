package com.example.bdsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {

    private static String APPLICATION_PROPERTIES = "application.properties";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(DatabaseConfig.class.getResource(fxmlFile));
            root = loader.load();
        } catch (IOException e) {
            logger.error("Cannot change the scene.", e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Connection getConnection() {
        try (InputStream resourceStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(resourceStream);

            String url = properties.getProperty("datasource.url");
            String username = properties.getProperty("datasource.username");
            String password = properties.getProperty("datasource.password");

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            logger.error("Configuration of the datasource was not successful.", e);
            return null;
        } catch (Exception e) {
            logger.error("Could not connect to the database.", e);
            return null;
        }
    }
}