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
import sample.anna.Classes.CheckInput;
import sample.anna.Classes.DBHandler;
import sample.anna.Classes.Matherials;

public class MatherialController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> matBox;

    @FXML
    private TextField nameField;

    @FXML
    private Button backButton;

    @FXML
    private TextField priceField;

    @FXML
    private Button addButton;

    @FXML
    private TextField deriverField;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button showButton;

    @FXML
    void comboChanged(ActionEvent event) {

    }


    DBHandler dbHandler;
     private ResultSet resultSet, resultAllSet;
    @FXML
    void initialize() {
        dbHandler =DBHandler.getDbHandler();

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

            String nameMat = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            String deriver = deriverField.getText();

           if(!CheckInput.checkName(nameMat))
           {
               AlertBox.display("Ошибка", "Название содержит недопустимые символы");
           }
            else if (!CheckInput.checkPrice(price))
            {
                AlertBox.display("Ошибка", "Цена не може быть отрицательной или равной 0");
            }
                 else  if(!CheckInput.checkName(deriver))
           {
               AlertBox.display("Ошибка", "Название поставщика содержит недопустимые символы");
           } else
               {
                   Matherials matherials = new Matherials(nameMat, price, deriver);
                  dbHandler.addMatherial(matherials);
               }





        });


       ArrayList<String> matArrayList = new ArrayList<>(10);
        resultSet = dbHandler.getSetMatherials();
        try {
            while(resultSet.next()){
                int i=1;
                matArrayList.add(resultSet.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> matList = FXCollections.observableArrayList(matArrayList);
        matBox.setItems(matList);

        showButton.setOnAction(event -> {

            resultAllSet = dbHandler.getAllSetMatherials();
            try {
                while(resultAllSet.next()){
                    if(matBox.getValue().equals(resultAllSet.getString(2))){
                       String  name = resultAllSet.getString(2);
                       int price = resultAllSet.getInt(3);
                        String deriver = resultAllSet.getString(4);

                        nameField.setText(name);
                        priceField.setText(String.valueOf(price));
                        deriverField.setText(deriver);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        changeButton.setOnAction(event -> {
            Matherials matherials = new Matherials();
            matherials.setName(matBox.getValue());
            matherials.setPrice(Integer.parseInt(priceField.getText()));
            dbHandler.updateMatherial(matherials);
        });
        deleteButton.setOnAction(event -> {
            Matherials matherials = new Matherials();
            matherials.setName(matBox.getValue());
            dbHandler.deleteMatherial(matherials);
        });



    }







}
