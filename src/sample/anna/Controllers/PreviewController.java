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

public class PreviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private ComboBox<Integer> brigadeBox;

    @FXML
    private TextField changeField;

    @FXML
    private ComboBox<String> matBox;

    @FXML
    private TextField priceField;

    @FXML
    private TextField deriverField;

    @FXML
    private Button showButton;


    DBHandler dbHandler;
    ResultSet resultSet1, resultAllSet1,resultSet2, resultAllSet2 ;
    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();


        ArrayList<Integer> arrayList = new ArrayList<>(10);
        resultSet1 = dbHandler.getSetBrigades();
        try {
            while(resultSet1.next()){
                int i=1;
                arrayList.add(resultSet1.getInt(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Integer> brigadeList = FXCollections.observableArrayList(arrayList);
        brigadeBox.setItems(brigadeList);


        ArrayList<String> matArrayList = new ArrayList<>(10);
        resultSet2 = dbHandler.getSetMatherials();
        try {
            while(resultSet2.next()){
                int i=1;
                matArrayList.add(resultSet2.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> matList = FXCollections.observableArrayList(matArrayList);
        matBox.setItems(matList);




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
        nextButton.setOnAction(event -> {
            nextButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/PreviewWorkRep.fxml"));
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
            int changeInt=0;
            resultAllSet1 = dbHandler.getAllSetBrigades();
            try {
                while(resultAllSet1.next()){
                    if(resultAllSet1.getInt(1)==brigadeBox.getValue()){
                        changeInt = resultAllSet1.getInt(2);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String change = String.valueOf(changeInt);
            changeField.setText(change);

            resultAllSet2 = dbHandler.getAllSetMatherials();
            try {
                while(resultAllSet2.next()){
                    if(matBox.getValue().equals(resultAllSet2.getString(2))){
                        int price = resultAllSet2.getInt(3);
                        String deriver = resultAllSet2.getString(4);

                        priceField.setText(String.valueOf(price));
                        deriverField.setText(deriver);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }






        });
    }


}

