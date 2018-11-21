package sample.anna.Controllers;

import java.io.IOException;
import java.net.URL;
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
import sample.anna.Classes.RegistrationCrash;

public class RegistrationCrashC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> descriptionBox;

    @FXML
    private ComboBox<String> regionBox;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private ComboBox<String> streetBox;

    @FXML
    private ComboBox<String> yearBox;

    @FXML
    private ComboBox<String> monthBox;

    @FXML
    private ComboBox<String> dayBox;

    @FXML
    private Button toRegisterCrasgButton;

    @FXML
    private Button backButton;


     DBHandler dbHandler;
    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();

        ObservableList<String> descriptionList = FXCollections.observableArrayList(
                "Электросети", "Водоснабжение", "Газовые сети", "Канализация", "Телефонная сеть");
        descriptionBox.setItems(descriptionList);

        ObservableList<String> regionList = FXCollections.observableArrayList(
                "Малиновский", "Киевский", "Приморский", "Суворовский");
        regionBox.setItems(regionList);

        ObservableList<String> streetList = FXCollections.observableArrayList(
                "переулок", "проспект", "улица");
        streetBox.setItems(streetList);

        ObservableList<String> yearList = FXCollections.observableArrayList(
                "2016", "2017", "2018");
        yearBox.setItems(yearList);

        ObservableList<String> monthList = FXCollections.observableArrayList(
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        monthBox.setItems(monthList);

        ObservableList<String> dayList = FXCollections.observableArrayList(
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30", "31");
        dayBox.setItems(dayList);





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
        toRegisterCrasgButton.setOnAction(event -> {

            RegistrationCrash crash = new RegistrationCrash();
            crash.setDescription(descriptionBox.getValue());
            crash.setRegion(regionBox.getValue());
            String address = streetBox.getValue() + " " + streetTextField.getText() + " " + numberTextField.getText();
            crash.setAddress(address);
            String date = yearBox.getValue() +"-" + monthBox.getValue() +"-" + dayBox.getValue();
            crash.setDate(date);

            dbHandler.addRegistrationCrash(crash);



        });




    }
}