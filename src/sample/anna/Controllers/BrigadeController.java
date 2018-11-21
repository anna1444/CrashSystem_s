package sample.anna.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.anna.Classes.AlertBox;
import sample.anna.Classes.Brigade;
import sample.anna.Classes.CheckInput;
import sample.anna.Classes.DBHandler;

public class BrigadeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField brigadeField;


    @FXML
    private ComboBox<Integer> brigadeBox;

    @FXML
    private TextField changeField;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private Button showButton;



     DBHandler dbHandler;
     ResultSet resultSet, resultAllSet;
     Brigade brigade;
    @FXML
    void comboChanged(ActionEvent event) {

    }

    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();


        ArrayList<Integer> arrayList = new ArrayList<>(10);
        resultSet = dbHandler.getSetBrigades();
        try {
            while(resultSet.next()){
                int i=1;
                arrayList.add(resultSet.getInt(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Integer> matList = FXCollections.observableArrayList(arrayList);
        brigadeBox.setItems(matList);




        showButton.setOnAction(event -> {
            int changeInt=0;
            String val = brigadeBox.getValue().toString();
            brigadeField.setText(val);

            resultAllSet = dbHandler.getAllSetBrigades();
            try {
                while(resultAllSet.next()){
                   if(resultAllSet.getInt(1)==brigadeBox.getValue()){
                       changeInt = resultAllSet.getInt(2);
                   }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String change = String.valueOf(changeInt);
            changeField.setText(change);
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
        addButton.setOnAction(event -> {
            if(!CheckInput.checkNumber(brigadeField.getText())) {
                AlertBox.display("Ошибка", "Номер бригады должен быть числом, а не символом");
            }
            int numberBrigade = Integer.parseInt(brigadeField.getText());
            if(!CheckInput.checkNumberNotZero(numberBrigade)){
                AlertBox.display("Ошибка", "Номер бригады должен быть больше 0 или ему не равняться");
            }

            int changeBrigade = Integer.parseInt(changeField.getText());
            if(!CheckInput.checkNumber(changeField.getText())){
                AlertBox.display("Ошибка", "Номер смены должен быть числом, а не символом");
            }
                Brigade brigade = new Brigade(numberBrigade, changeBrigade);
                 dbHandler.addBrigade(brigade);

            });
        changeButton.setOnAction(event -> {
            brigade = new Brigade(brigadeBox.getValue(), Integer.parseInt(changeField.getText()));
            dbHandler.updateBrigade(brigade);
        });
        deleteButton.setOnAction(event -> {
          Brigade brigade = new Brigade();
          brigade.setNumber(brigadeBox.getValue());
          dbHandler.deleteBrigade(brigade);
        });




    }


}
