package com.example.bdsproject.controllers;

import com.example.bdsproject.configuration.DatabaseConfig;
import com.example.bdsproject.configuration.DatabaseQueries;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {
    @FXML
    public Label tableSelect;
    @FXML
    public Label idSelect;
    @FXML
    public ChoiceBox tableChoice;
    @FXML
    public TextField insertTextField;
    @FXML
    public Label mainTextLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button endProgramButton;
    @FXML
    private Button menuButton;

    private static String choice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableChoice.setItems(FXCollections.observableArrayList(
                "Choose the table!","Address","Bank","Brand","Car","Contact","Credential",
                "Customer","Employee","Engine","Insurance","Name","Rent","Status"));

        tableChoice.setValue("Choose the table!");

        tableChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                choice = ((String) tableChoice.getItems().get((Integer) t1)).toLowerCase(Locale.ROOT);
            }
        });

        deleteButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                int ID = Integer.parseInt(insertTextField.getText());
                DatabaseQueries.deleteQuery(event, choice, ID);
                DatabaseConfig.changeScene(event, "Menu.fxml");
            }
        });

        endProgramButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Platform.exit();
            }
        });

        menuButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Menu.fxml");
            }
        });
    }
}
