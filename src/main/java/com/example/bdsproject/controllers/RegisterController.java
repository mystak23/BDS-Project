package com.example.bdsproject.controllers;

import com.example.bdsproject.configuration.DatabaseAccess;
import com.example.bdsproject.configuration.DatabaseConfig;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public PasswordField passwordTextField2;
    @FXML
    public Button registerButton;
    @FXML
    public Button endProgramButton;
    @FXML
    public Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        endProgramButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Platform.exit();
            }
        });

        loginButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Login.fxml");
            }
        });

        registerButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (passwordTextField.getText().equals(passwordTextField2.getText())) {
                    DatabaseAccess.signUpUser(event, usernameTextField.getText(), passwordTextField.getText());
                    DatabaseConfig.changeScene(event, "Login.fxml");
                } else {
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setContentText(("These passwords are not the same!"));
                    alert.show();
                }
            }
        });

    }
}

