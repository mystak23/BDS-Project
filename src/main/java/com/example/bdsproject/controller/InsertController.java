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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class InsertController implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private ChoiceBox tableChoice;
    @FXML
    private TextField insert1;
    @FXML
    private TextField insert2;
    @FXML
    private TextField insert3;
    @FXML
    private TextField insert4;
    @FXML
    private TextField insert5;
    @FXML
    private TextField insert6;
    @FXML
    private TextField insert7;
    @FXML
    private TextField insert8;
    @FXML
    private TextField insert9;
    @FXML
    private Button insertButton;
    @FXML
    private Button endProgramButton;
    @FXML
    private Button menuButton;

    private static int countOfValues;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableChoice.setItems(FXCollections.observableArrayList(
                "Choose the table!","Address","Bank","Brand","Car","Contact","Credential",
                "Customer","Employee","Engine","Insurance","Name","Rent","Status"));

        tableChoice.setValue("Choose the table!");

        tableChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String choice = (String) tableChoice.getItems().get((Integer) t1);
                switch (choice) {
                    case "Address":
                        label1.setText("city");
                        label2.setText("house_number");
                        label3.setText("is_customers_address");
                        label4.setText("is_our_workplace");
                        label5.setText("street");
                        label6.setText("zip_code");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                    case "Bank":
                        label1.setText("name");
                        label2.setText("nationality");
                        label3.setText("residence");
                        label4.setVisible(false);
                        label5.setVisible(false);
                        label6.setVisible(false);
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert4.setVisible(false);
                        insert5.setVisible(false);
                        insert6.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 3;
                        break;
                    case "Brand":
                        label1.setText("capacity");
                        label2.setText("color");
                        label3.setText("concern");
                        label4.setText("country");
                        label5.setText("model");
                        label6.setText("name");
                        label7.setText("year");
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 7;
                        break;
                    case "Car":
                        label1.setText("brand_id");
                        label2.setText("equipment_id");
                        label3.setText("is_available");
                        label4.setText("licence_plate");
                        label5.setText("status_id");
                        label6.setText("tank_condition");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                    case "Contact":
                        label1.setText("data_box");
                        label2.setText("email");
                        label3.setText("phone_number");
                        label4.setVisible(false);
                        label5.setVisible(false);
                        label6.setVisible(false);
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert4.setVisible(false);
                        insert5.setVisible(false);
                        insert6.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 3;
                        break;
                    case "Credential":
                        label1.setText("driving_licence_id");
                        label2.setText("id_card_id");
                        label3.setText("passport_id");
                        label4.setVisible(false);
                        label5.setVisible(false);
                        label6.setVisible(false);
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert4.setVisible(false);
                        insert5.setVisible(false);
                        insert6.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 3;
                        break;
                    case "Customer":
                        label1.setText("address_id");
                        label2.setText("contact_id");
                        label3.setText("credential_id");
                        label4.setText("date_of_birth");
                        label5.setText("name_id");
                        label6.setText("nationality");
                        label7.setText("time of registration");
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 7;
                        break;
                    case "Employee":
                        label1.setText("address_id");
                        label2.setText("contact_id");
                        label3.setText("employed_since");
                        label4.setText("name_id");
                        label5.setText("place_of_employment");
                        label6.setText("wage");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                    case "Engine":
                        label1.setText("ccm_volume");
                        label2.setText("has_four_wheel_drive");
                        label3.setText("has_turbo");
                        label4.setText("kw_performance");
                        label5.setText("name");
                        label6.setText("nm_torque");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                    case "Insurance":
                        label1.setText("accident");
                        label2.setText("extra_price");
                        label3.setText("lost_keys");
                        label4.setText("rubbing");
                        label5.setText("scratching_the_car");
                        label6.setText("theft");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                    case "Name":
                        label1.setText("first_name");
                        label2.setText("last_name");
                        label3.setText("title_before_name");
                        label4.setText("title_after_name");
                        label5.setVisible(false);
                        label6.setVisible(false);
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert5.setVisible(false);
                        insert6.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 4;
                        break;
                    case "Rent":
                        label1.setText("car_id");
                        label2.setText("customer_id");
                        label3.setText("date_of_pickup");
                        label4.setText("date_of_return");
                        label5.setText("employee_id");
                        label6.setText("insurance_id");
                        label7.setText("pickup_address_id");
                        label8.setText("price_of_rent");
                        label9.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 8;
                        break;
                    case "Status":
                        label1.setText("bodywork");
                        label2.setText("engine_id");
                        label3.setText("fuel");
                        label4.setText("has_winter_tyres");
                        label5.setText("mileage");
                        label6.setText("tuning_id");
                        label7.setVisible(false);
                        label8.setVisible(false);
                        label9.setVisible(false);
                        insert7.setVisible(false);
                        insert8.setVisible(false);
                        insert9.setVisible(false);
                        countOfValues = 6;
                        break;
                }
            }
        });

        insertButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                String table = ((String) tableChoice.getValue()).toLowerCase();
                String query = null;
                switch (countOfValues) {
                    case 3:
                        query = "INSERT INTO public." + table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ");";
                        break;
                    case 4:
                        query = "INSERT INTO public." + table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ", "
                                + label4.getText().toLowerCase(Locale.ROOT) + ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ", " + insert4.getText() + ");";
                        break;
                    case 5:
                        query = "INSERT INTO public." + table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ", "
                                + label4.getText().toLowerCase(Locale.ROOT) + ", " + label5.getText().toLowerCase(Locale.ROOT) + ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ", " + insert4.getText() + ", "
                                + insert5.getText()  + ");";
                        break;
                    case 6:
                        query = "INSERT INTO public." + table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ", "
                                + label4.getText().toLowerCase(Locale.ROOT) + ", " + label5.getText().toLowerCase(Locale.ROOT) + ", "
                                + label6.getText().toLowerCase(Locale.ROOT) + ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ", " + insert4.getText() + ", "
                                + insert5.getText() + ", " + insert6.getText()  + ");";
                        break;
                    case 7:
                        query = "INSERT INTO public."+ table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ", "
                                + label4.getText().toLowerCase(Locale.ROOT) + ", " + label5.getText().toLowerCase(Locale.ROOT) + ", "
                                + label6.getText().toLowerCase(Locale.ROOT) + ", " + label7.getText().toLowerCase(Locale.ROOT) + ", "
                                + ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ", " + insert4.getText() + ", "
                                + insert5.getText() + ", " + insert6.getText() + ", " + insert7.getText() + ");";
                        break;
                    case 8:
                        query = "INSERT INTO public." + table + " (" + label1.getText().toLowerCase(Locale.ROOT) + ", "
                                + label2.getText().toLowerCase(Locale.ROOT) + ", " + label3.getText().toLowerCase(Locale.ROOT) + ", "
                                + label4.getText().toLowerCase(Locale.ROOT) + ", " + label5.getText().toLowerCase(Locale.ROOT) + ", "
                                + label6.getText().toLowerCase(Locale.ROOT) + ", " + label7.getText().toLowerCase(Locale.ROOT) + ", "
                                + label8.getText().toLowerCase(Locale.ROOT)+ ") VALUES ("
                                + insert1.getText() + ", " + insert2.getText() + ", " + insert3.getText() + ", " + insert4.getText() + ", "
                                + insert5.getText() + ", " + insert6.getText() + ", " + insert7.getText() + ", "+ insert8.getText() + ");";
                        break;
                }
                DatabaseQuery.insertQuery(event, query);
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
