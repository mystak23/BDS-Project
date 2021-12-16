package com.example.bdsproject.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConfig {

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try (InputStream resourceStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(resourceStream);
            config.setJdbcUrl(properties.getProperty("datasource.url"));
            config.setUsername(properties.getProperty("datasource.username"));
            config.setPassword(properties.getProperty("datasource.password"));
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            Connection connection = null;
            return connection;
        }
    }

    public static void closeConnection() {
        ds = null;
        config = null;
        ds.close();
    }

    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(DatabaseConfig.class.getResource(fxmlFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}