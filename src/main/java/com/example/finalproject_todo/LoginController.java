package com.example.finalproject_todo;

import com.example.finalproject_todo.Animations.Shaker;
import com.example.finalproject_todo.Database.DatabaseHandler;
import com.example.finalproject_todo.model.User;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    public int userId;
    public String firstname;
    public String lastname;


    @FXML
    private ResourceBundle resources;


    @FXML
    private URL location;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;


    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;
    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();


        loginButton.setOnAction(event -> {
            String loginText = loginUsername.getText().trim();
            String loginPWD = loginPassword.getText().trim();

            User user = new User();
            user.setUesrName(loginText);
            user.setPassword(loginPWD);

            try {
                ResultSet userRow = databaseHandler.getUser(user);

                int counter = 0;

                while (userRow.next()){
                    counter++;
                    firstname = userRow.getString("firstname");
                    lastname = userRow.getString("lastname");
                    userId = userRow.getInt("userid");
                    System.out.println("Welcome, "+firstname+" "+lastname);
                }

                if(counter ==1 ) {
                    showAddItemScreen();
                    System.out.println("Login Successful!");
                }else {
                    Shaker shaker = new Shaker(loginUsername);
                    shaker.shake();
                    Shaker shakerp = new Shaker(loginPassword);
                    shakerp.shake();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        loginSignupButton.setOnAction(event -> {
            loginSignupButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signup-view.fxml"));

            try{
                loader.load();

            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void showAddItemScreen(){
        loginSignupButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addItem.fxml"));


        try{
            loader.load();

        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();
        addItemController.setUserId(userId,firstname,lastname);

        stage.showAndWait();

    }


}