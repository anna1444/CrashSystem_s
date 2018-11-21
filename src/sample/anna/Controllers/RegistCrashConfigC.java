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

public class RegistCrashConfigC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showButton;

    @FXML
    private ComboBox<Integer> registBox;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField regionField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField dateField;

    @FXML
    private Button backButton;

     DBHandler dbHandler;
     ResultSet resultSet, resultAllSet;
    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();

        ArrayList<Integer> arrayList = new ArrayList<>(10);
        resultSet = dbHandler.getSetRegisr();
        try {
            while(resultSet.next()){
                int i=1;
                arrayList.add(resultSet.getInt(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Integer> registList = FXCollections.observableArrayList(arrayList);
        registBox.setItems(registList);

        showButton.setOnAction(event -> {

            resultAllSet = dbHandler.getAllSetRegist();
            try {
                while(resultAllSet.next()){
                    if(resultAllSet.getInt(1)==registBox.getValue()){
                        String theme = resultAllSet.getString(2);
                        String region = resultAllSet.getString(3);
                        String address = resultAllSet.getString(4);
                        String date = resultAllSet.getString(5);


                        descriptionField.setText(theme);
                        regionField.setText(region);
                        addressField.setText(address);
                        dateField.setText(date);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/MenuConfig.fxml"));
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

    }
}
