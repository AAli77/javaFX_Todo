package com.example.finalproject_todo;

import com.example.finalproject_todo.Database.DatabaseHandler;
import com.example.finalproject_todo.model.Task;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private TextField taskField;

    @FXML
    private Label successLabel;

    @FXML
    private JFXButton todoButton;

    @FXML
    void taskDescription(ActionEvent event) {

    }

    @FXML
    void taskField(ActionEvent event) {

    }



    private DatabaseHandler databaseHandler;

    private int uid;
    public int test;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
            task.setDateCreated(timestamp);

            String taskText  = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if(!taskText.equals("") || !taskDescription.equals("")){

                System.out.println("User Id: "+AddItemController.userId);
                task.setUserId(AddItemController.userId);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                try {
                    databaseHandler.insertTask(task);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("Task added successfully.....!");

                successLabel.setVisible(true);
                todoButton.setVisible(true);
                int taskNumber = databaseHandler.getTaskCount(AddItemController.userId);

                taskField.setText("");
                descriptionField.setText("");


                todoButton.setText("My ToDo: " + taskNumber);
                todoButton.setOnAction(event1 -> {
                    System.out.println("Clicked by todo");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("list-view.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                });




            }else System.out.println("Nothing Added");
        });
    }

    public void setUserId(int uid) {
        this.uid = uid;
        test = uid;
        //System.out.println("AddItemFormController + "+this.uid);
        //System.out.println(String.valueOf(getUserId()));

    }

    public int getUserId() {
        return this.uid;
    }

}