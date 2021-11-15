package com.example.finalproject_todo;


import com.example.finalproject_todo.Database.DatabaseHandler;
import com.example.finalproject_todo.model.Task;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField descriptionField;

    @FXML
    private JFXListView<Task> listTasks;

    @FXML
    private ListView<Task> list2Task;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private TextField taskField;

    @FXML
    void taskDescription(ActionEvent event) {

    }

    @FXML
    void taskField(ActionEvent event) {

    }


    //ObservableList<String> listview = FXCollections.observableArrayList("John", "Amir", "Paul", "Java");

    private ObservableList<Task> tasks;
    private DatabaseHandler dbHandler;

    @FXML
    void initialize() throws SQLException {
        //listTasks.setItems(listview);
        //listTasks.setCellFactory(param -> new JFXCell<>());

        tasks = FXCollections.observableArrayList();
        dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getTasksListByUser(AddItemController.userId);


        while (resultSet.next()){
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDescription(resultSet.getString("description"));
            task.setDateCreated(resultSet.getTimestamp("datecreated"));
            String listout = resultSet.getString("task");

            tasks.addAll(task);

        }



        //Task myTask = new Task();
        //myTask.setTask("Clear Car");
        //myTask.setDescription("have to clean it before it's too late");
        //myTask.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));

        //Task myTask2 = new Task();
        //myTask2.setTask("Pay Bills");
        //myTask2.setDescription("Pay Bills for Utility");
        //myTask2.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));

        //tasks.addAll(myTask,myTask2);


        listTasks.setItems(tasks);
        listTasks.setCellFactory(CellviewController -> new CellviewController());
        //list2Task.setCellFactory(CellviewController -> new CellviewController());


    }


}

