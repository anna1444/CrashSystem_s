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

public class WorkerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> workerBox;

    @FXML
    private TextField lastnameField;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField birtdayField;

    @FXML
    private TextField brigadeField;

    @FXML
    private Button addButton;

    @FXML
    private TextField nameField;

    @FXML
    private Button showButton;

    DBHandler dbHandler;
     private ResultSet resultSet, resultAllSet;
    Worker worker;
    @FXML
    void comboChanged(ActionEvent event) {

    }

    @FXML
    void initialize() {
        dbHandler = DBHandler.getDbHandler();
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

        ArrayList<String> arrayList = new ArrayList<>(10);
        resultSet = dbHandler.getSetWorkers();
        try {
            while(resultSet.next()){
                int i=1;
                arrayList.add(resultSet.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> workerList = FXCollections.observableArrayList(arrayList);
        workerBox.setItems(workerList);


        addButton.setOnAction(event -> {

            if(!CheckInput.checkName(nameField.getText()) ) {
                AlertBox.display("Ошибка", "Проверьте правильность написания имени");
            }
            if(!CheckInput.checkName(lastnameField.getText()) ) {
                AlertBox.display("Ошибка", "Проверьте правильность написания отчества");
            }
            if(!CheckInput.checkName(surnameField.getText()) ) {
                AlertBox.display("Ошибка", "Проверьте правильность написания фамилии");
            }
            if(!CheckInput.checkNumber(telephoneField.getText()) ) {
                AlertBox.display("Ошибка", "Телефон должен содержать только цифры");
            }
            worker = new Worker(nameField.getText(), surnameField.getText(), lastnameField.getText(), telephoneField.getText(),
                    birtdayField.getText(), 3, Integer.parseInt(brigadeField.getText()));

            dbHandler.addWorker(worker);


        });
        showButton.setOnAction(event -> {
            String name = null;
            String surname = null;
            String lastname = null;
            String telephone = null;
            String birthday = null;
            int idBrigade = 0;


            String surn = workerBox.getValue().toString();

            resultAllSet = dbHandler.getAllSetWorkers();
            try {
                while(resultAllSet.next()){
                    if(workerBox.getValue().equals(resultAllSet.getString(3))){
                        name = resultAllSet.getString(2);
                        surname = resultAllSet.getString(3);
                        lastname = resultAllSet.getString(4);
                        telephone = resultAllSet.getString(5);
                        birthday = resultAllSet.getString(6);
                        idBrigade = resultAllSet.getInt(7);


                        nameField.setText(name);
                        surnameField.setText(surname);
                        lastnameField.setText(lastname);
                        telephoneField.setText(telephone);
                        birtdayField.setText(birthday);
                        brigadeField.setText(String.valueOf(idBrigade));

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        changeButton.setOnAction(event -> {
           worker = new Worker();
           worker.setSurname(workerBox.getValue());
           worker.setTelephone(telephoneField.getText());
           worker.setIdBrigade(Integer.parseInt(brigadeField.getText()));
            dbHandler.updateWorker(worker);
        });
        deleteButton.setOnAction(event -> {
            Worker worker = new Worker();
            worker.setSurname(workerBox.getValue());
            dbHandler.deleteWorker(worker);
        });

    }
}


