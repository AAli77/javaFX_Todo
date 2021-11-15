package com.example.finalproject_todo;

import com.example.finalproject_todo.Database.Const;
import com.example.finalproject_todo.Database.DatabaseHandler;

import com.example.finalproject_todo.model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signupFirstName;

    @FXML
    private TextField signupLastName;

    @FXML
    private TextField signupUsername;

    @FXML
    private TextField signupLocation;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private JFXCheckBox signUpCBMale;

    @FXML
    private JFXCheckBox signUpCBFemale;

    @FXML
    private JFXButton signupButton;

    @FXML
    void initialize() {

        signupButton.setOnAction(event -> {
            createUser();
            System.out.println("Record added....!");
        });

    }

    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstname = signupFirstName.getText();
        String lastname = signupLastName.getText();
        String username = signupUsername.getText();
        String password = signupPassword.getText();
        String location = signupLocation.getText();

        String gender = "";
        if (signUpCBFemale.isSelected()){
            gender = "Female";
        }else gender="Male";

        User user = new User(firstname, lastname, username,password, location, gender);

        try {
            //databaseHandler.signUpUser(signupFirstName.getText(), signupLastName.getText(),signupUsername.getText(),
            //        signupPassword.getText(),signupLocation.getText(),"Male");
            databaseHandler.signUpUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
