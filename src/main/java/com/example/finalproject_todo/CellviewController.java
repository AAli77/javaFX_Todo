package com.example.finalproject_todo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.finalproject_todo.Database.DatabaseHandler;
import com.example.finalproject_todo.model.Task;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CellviewController extends JFXListCell<Task> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView iconImageview;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label taskLabel;

    private FXMLLoader fxmlLoader;
    private DatabaseHandler dbHandler;

    @FXML
    void initialize() {

    }

    @Override
    public void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if (empty || myTask==null){
            setText(null);
            setGraphic(null);
        }else {
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("cell-view.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                taskLabel.setText(myTask.getTask());
                dateLabel.setText(myTask.getDateCreated().toString());
                descriptionLabel.setText(myTask.getDescription());
                //System.out.println("User id from cell controll "+AddItemController.userId);

                int taskId = myTask.getTaskId();

                deleteButton.setOnMouseClicked(event -> {
                    System.out.println("delete button clicked....!");
                    getListView().getItems().remove(getItem());
                    dbHandler.deleteTask(AddItemController.userId,taskId);
                });

                setText(null);
                setGraphic(rootAnchorPane);
            }
        }
    }
}

