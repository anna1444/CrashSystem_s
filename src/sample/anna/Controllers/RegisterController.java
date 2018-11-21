package sample.anna.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.anna.Classes.*;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button idNextButton;

    @FXML
    private Button idRegistrationButton;

    @FXML
    private TextField codeField;

    @FXML
    private Button idBackButton;

    DBHandler dbHandler;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        dbHandler = DBHandler.getDbHandler();

            idRegistrationButton.setOnAction(event -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String code = codeField.getText();

                User userAdmin = new User(username, password, 1);
                User userManager = new User(username, password, 2);

                if(code.equals(Const.ROLE_ADMIN_CODE) && checkInput(username, password)==true){
                    dbHandler.signUpUser(userAdmin);
                }
                if(code.equals(Const.ROLE_OPERATOR_CODE) && checkInput(username, password )){
                     dbHandler.signUpUser(userManager);}

                 else {
                    AlertBox.display("Ошибка", "Введены неправильные данные");
                }
            });

            idBackButton.setOnAction(event -> {
                idBackButton.getScene().getWindow().hide();
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
                stage.showAndWait();
            });
    }


            private  boolean checkInput(String username, String password){
                 boolean correctInput=false;
               if(CheckInput.checkName(username) && CheckInput.checkPassword(password)){
                   correctInput = true;
               } return correctInput;
            }






}


