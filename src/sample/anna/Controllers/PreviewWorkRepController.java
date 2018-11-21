package sample.anna.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.anna.Classes.DBHandler;

public class PreviewWorkRepController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showButton;

    @FXML
    private Button nextButton;

    @FXML
    private ComboBox<String> workerBox;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Integer> registBox;

    @FXML
    private TextField themeField;

    @FXML
    private TextField regionField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField birthdayField;

    @FXML
    private TextField brigadeField;

    @FXML
    private TextField adressField;

    @FXML
    private TextField dateField;

    @FXML
    private Button backButton;

    DBHandler dbHandler;
    ResultSet resultSet1, resultAllSet1,resultSet2, resultAllSet2 ;

    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();

        ArrayList<String> arrayList = new ArrayList<>(10);
        resultSet1 = dbHandler.getSetWorkers();
        try {
            while(resultSet1.next()){
                int i=1;
                arrayList.add(resultSet1.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> workerList = FXCollections.observableArrayList(arrayList);
        workerBox.setItems(workerList);


        ArrayList<Integer> registArrayList = new ArrayList<>(10);
        resultSet2 = dbHandler.getSetRegisr();
        try {
            while(resultSet2.next()){
                int i=1;
                registArrayList.add(resultSet2.getInt(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Integer> registList = FXCollections.observableArrayList(registArrayList);
        registBox.setItems(registList);


       /* nextButton.setOnAction(event -> {
            nextButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/PreviewRepair.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("cant load");
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });*/
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/Enter.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("cant load");
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        showButton.setOnAction(event -> {
            String name = null;
            String surname = null;
            String lastname = null;
            String telephone = null;
            String birthday = null;
            int idBrigade = 0;

            String surn = workerBox.getValue().toString();

            resultAllSet1 = dbHandler.getAllSetWorkers();
            try {
                while(resultAllSet1.next()){
                    if(workerBox.getValue().equals(resultAllSet1.getString(3))){
                        name = resultAllSet1.getString(2);
                        surname = resultAllSet1.getString(3);
                        lastname = resultAllSet1.getString(4);
                        telephone = resultAllSet1.getString(5);
                        birthday = resultAllSet1.getString(6);
                        idBrigade = resultAllSet1.getInt(7);


                        nameField.setText(name);
                        lastnameField.setText(lastname);
                        telephoneField.setText(telephone);
                        birthdayField.setText(birthday);
                        brigadeField.setText(String.valueOf(idBrigade));

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            resultAllSet2 = dbHandler.getAllSetRegist();
            try {
                while(resultAllSet2.next()){
                    if(resultAllSet2.getInt(1)==registBox.getValue()){
                        String theme = resultAllSet2.getString(2);
                        String region = resultAllSet2.getString(3);
                        String address = resultAllSet2.getString(4);
                        String date = resultAllSet2.getString(5);


                        themeField.setText(theme);
                        regionField.setText(region);
                        adressField.setText(address);
                        dateField.setText(date);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }
}

