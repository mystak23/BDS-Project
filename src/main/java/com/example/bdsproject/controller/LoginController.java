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
                if (usernameTextField.getText().equals("postgres") || usernameTextField.getText().equals("owner")) {
                    MenuController.canInsert = true;
                    MenuController.canSelect = true;
                    MenuController.canDelete = true;
                    MenuController.canUpdate = true;
                    DatabaseAccess.logInUser(event, usernameTextField.getText(), passwordTextField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You can select, insert, update and delete data.");
                    alert.showAndWait();
                } else if (usernameTextField.getText().equals("employee")) {
                    MenuController.canInsert = true;
                    MenuController.canSelect = true;
                    DatabaseAccess.logInUser(event, usernameTextField.getText(), passwordTextField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You can select and insert");
                    alert.showAndWait();
                } else if (usernameTextField.getText().equals("hacker")) {
                    MenuController.canInject = true;
                    DatabaseAccess.logInUser(event, usernameTextField.getText(), passwordTextField.getText());
                } else {
                    DatabaseAccess.logInUser(event, usernameTextField.getText(), passwordTextField.getText());
                }
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

