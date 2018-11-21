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
import sample.anna.Classes.*;

public class RepairController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> workerBox;

    @FXML
    private TextField registField;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private Button showButton;

    @FXML
    private ComboBox<String> mathBox;

    @FXML
    private TextField workerField;

    @FXML
    private ComboBox<Integer> registBox;

    @FXML
    private TextField mathField;

    @FXML
    private TextField amount;

    @FXML
    private TextField dateBeginField;

    @FXML
    private TextField dateFinishField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<?> repairBox;

    @FXML
    void comboChanged(ActionEvent event) {

    }
    DBHandler dbHandler;
    ResultSet resultSet1, resultSetMat, resultSetWork;
    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();

        //получить сет заявок
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        resultSet1 = dbHandler.getSetRegisr();
        try {
            while(resultSet1.next()){
                int i=1;
                arrayList.add(resultSet1.getInt(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ObservableList<Integer> registList = FXCollections.observableArrayList(arrayList);
        registBox.setItems(registList);



        // получить сет материалов

        ArrayList<String> matArrayList = new ArrayList<>(10);
        resultSetMat = dbHandler.getSetMatherials();
        try {
            while(resultSetMat.next()){
                int i=1;
                matArrayList.add(resultSetMat.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> matList = FXCollections.observableArrayList(matArrayList);
        mathBox.setItems(matList);


        // получить сет рабочих

        ArrayList<String> workerArrayList = new ArrayList<>(10);
        resultSetWork = dbHandler.getSetWorkers();
        try {
            while(resultSetWork.next()){
                int i=1;
                workerArrayList.add(resultSetWork.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> workerList = FXCollections.observableArrayList(workerArrayList);
        workerBox.setItems(workerList);




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

         Repair repair = new Repair();
         repair.setIdWorker(workerField.getText());
         repair.setIdMatherials(mathField.getText());
         repair.setAmountMat(Integer.parseInt(amount.getText()));
         repair.setIdRegist(Integer.parseInt(registField.getText()));
         repair.setPrice(Double.parseDouble(priceField.getText()));
         repair.setDate_begin(dateBeginField.getText());
         repair.setDate_finish(dateFinishField.getText());

         dbHandler.addRepair(repair);

     });
     showButton.setOnAction(event -> {
         registField.setText(registBox.getValue().toString());
         mathField.setText(mathBox.getValue());
         workerField.setText(workerBox.getValue());
     });
    }
}
