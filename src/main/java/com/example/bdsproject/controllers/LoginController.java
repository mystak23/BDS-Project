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

public class LoginController implements Initializable {
    @FXML
    private Button signInButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button registerButton;
    @FXML
    private Button endProgramButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signInButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseAccess.logInUser(event, usernameTextField.getText(), passwordTextField.getText());
            }
        });

        registerButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Register.fxml");
            }
        });

        endProgramButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Platform.exit();
            }
        });
    }
}

