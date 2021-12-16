package com.example.bdsproject.controllers;

import com.example.bdsproject.configuration.DatabaseConfig;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public TableView tableView;
    @FXML
    private Button deleteButton;
    @FXML
    private Button selectButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button endProgramButton;
    @FXML
    public TextField queryTextField;
    @FXML
    private Button viewAllDataButton;
    @FXML
    private Button signOutButton;

    private static TableColumn col;

    public void selectQuery(String query) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ObservableList<ObservableList> data = null;
        try {
            connection = DatabaseConfig.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            data = FXCollections.observableArrayList();
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                final int j = i;
                col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableView.getColumns().addAll(col);
            }
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong SQL query!");
            alert.show();
            e.printStackTrace();
        } finally {
            DatabaseConfig.closeConnection();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        queryTextField.setVisible(true);

        viewAllDataButton.setText("VIEW ALL CARS");

        viewAllDataButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    tableView.getItems().clear();
                }
                for (int i = 0; i < tableView.getColumns().size(); i++) {
                    tableView.getColumns().clear();
                }
                selectQuery("SELECT * FROM public.car");
            }
        });

        selectButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    tableView.getItems().clear();
                }
                for (int i = 0; i < tableView.getColumns().size(); i++) {
                    tableView.getColumns().clear();
                }
                if (!queryTextField.getText().isEmpty()) {
                    selectQuery(queryTextField.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Empty query.");
                    alert.show();
                }
            }
        });

        endProgramButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Platform.exit();
            }
        });

        insertButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Alert notification = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                notification.setContentText("For strings, enter a value into quotation marks. For example: 'Java'.");
                notification.showAndWait();
                notification.setContentText("For booleans, enter true or false. For example: true");
                notification.showAndWait();
                notification.setContentText("For dates, enter a YYYY-MM-DD. For example: 2021-12-24");
                notification.showAndWait();
                DatabaseConfig.changeScene(event, "Insert.fxml");
            }
        });

        updateButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Update.fxml");
            }
        });

        deleteButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Delete.fxml");
            }
        });

        signOutButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                DatabaseConfig.changeScene(event, "Login.fxml");
            }
        });
    }
}
