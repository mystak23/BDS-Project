package com.example.bdsproject.controller;

import com.example.bdsproject.DatabaseConfig;
import com.example.bdsproject.config.DatabaseAccess;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField passwordTextField2;
    @FXML
    private Button registerButton;
    @FXML
    private Button endProgramButton;
    @FXML
    private Button loginButton;

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

