package com.example.bdsproject.controller;

import com.example.bdsproject.DatabaseConfig;
import com.example.bdsproject.config.DatabaseQuery;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private ChoiceBox tableChoice;
    @FXML
    private ChoiceBox columnChoice;
    @FXML
    private TextField idInsert;
    @FXML
    private TextField insertTextField;
    @FXML
    private Button updateButton;
    @FXML
    private Button endProgramButton;
    @FXML
    private Button menuButton;

    private static String choice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableChoice.setItems(FXCollections.observableArrayList(
                "Choose the table!", "Address", "Bank", "Brand", "Car", "Contact", "Credential",
                "Customer", "Employee", "Engine", "Insurance", "Name", "Rent", "Status"));

        tableChoice.setValue("Choose the table!");

        columnChoice.setVisible(false);

        tableChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                columnChoice.setVisible(true);
                choice = ((String) tableChoice.getItems().get((Integer) t1)).toLowerCase(Locale.ROOT);
                if (choice.equals("address")) {
                    columnChoice.setItems(FXCollections.observableArrayList("city", "house_number", "is_customers_address", "is_our_workplace", "street", "zip_code"));
                }

                if (choice.equals("bank")) {
                    columnChoice.setItems(FXCollections.observableArrayList("name", "nationality", "residence"));
                }

                if (choice.equals("brand")) {
                    columnChoice.setItems(FXCollections.observableArrayList("capacity", "color", "concern", "country", "model", "name", "year"));
                }

                if (choice.equals("car")) {
                    columnChoice.setItems(FXCollections.observableArrayList("brand_id", "equipment_id", "is_available", "licence_plate", "status_id", "tank_condition"));
                }

                if (choice.equals("contact")) {
                    columnChoice.setItems(FXCollections.observableArrayList("data_box", "email", "phone_number"));
                }

                if (choice.equals("credential")) {
                    columnChoice.setItems(FXCollections.observableArrayList("driving_licence_id", "id_card_id", "passport_id"));
                }

                if (choice.equals("customer")) {
                    columnChoice.setItems(FXCollections.observableArrayList("address_id", "contact_id", "credential_id", "date_of_birth", "name_id", "nationality", "time_of_registration"));
                }

                if (choice.equals("employee")) {
                    columnChoice.setItems(FXCollections.observableArrayList("address_id", "contact_id", "employed_since", "name_id", "place_of_employment", "wage"));
                }

                if (choice.equals("engine")) {
                    columnChoice.setItems(FXCollections.observableArrayList("ccm_volume", "has_four_wheel_drive", "has_turbo", "kW_performance", "name", "nm_torque"));
                }

                if (choice.equals("insurance")) {
                    columnChoice.setItems(FXCollections.observableArrayList("accident", "extra_price", "lost_keys", "rubbing", "scratching_the_car", "theft"));
                }

                if (choice.equals("name")) {
                    columnChoice.setItems(FXCollections.observableArrayList("first_name", "last_name", "title_before_name", "title_after_name"));
                }

                if (choice.equals("rent")) {
                    columnChoice.setItems(FXCollections.observableArrayList("car_id", "customer_id", "date_of_pickup", "date_of_return", "employee_id", "insurance_id", "pickup_address_id", "price_of_rent"));
                }

                if (choice.equals("status")) {
                    columnChoice.setItems(FXCollections.observableArrayList("bodywork", "engine_id", "fuel", "has_winter_tyres", "mileage", "tuning_id"));
                }
            }
        });

        updateButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                String table = ((String) tableChoice.getValue()).toLowerCase();
                String column = ((String) columnChoice.getValue());
                int id = Integer.parseInt(idInsert.getText());
                String value = insertTextField.getText();
                System.out.println(table + column + id + value);
                DatabaseQuery.updateQuery(event, table, id, column, value);
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
