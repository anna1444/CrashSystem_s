package sample.anna.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.anna.Classes.AlertBox;
import sample.anna.Classes.Const;
import sample.anna.Classes.DBHandler;
import sample.anna.Classes.User;

public class EnterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField messageField;

    @FXML
    private Button idEnterButton;

    @FXML
    private Button idRegisterButton;

    DBHandler dbHandler;

    @FXML
    void initialize() {

        dbHandler = DBHandler.getDbHandler();

        idEnterButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (!username.equals("") && !password.equals("")) {
                loginUser(username, password);
            } else {
                AlertBox.display("Ошибка", "Вы ввели неполные данные");
            }

        });


        idRegisterButton.setOnAction(event -> {
            idRegisterButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/Registration.fxml"));
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


    private void loginUser(String username, String password) {
        User user = new User("anna", "23", 1);
        user.setUsername(username);
        user.setPassword(password);

        user = dbHandler.getUser(user);
        if (user != null) {
            if (user.getIdPost() == 2) {

                idEnterButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/anna/View/RegistrationCrash.fxml"));
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
            } else {
                idEnterButton.getScene().getWindow().hide();
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
            }


        }
    }}
