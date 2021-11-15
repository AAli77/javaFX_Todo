package com.example.finalproject_todo;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.finalproject_todo.Animations.Shaker;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AddItemController {
    public static int userId;
    private String fName;
    private String lName;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label lblName;

    @FXML
    private Label noTaskLabel;

    @FXML
    private AnchorPane rootAncherPane;

    @FXML
    void initialize() {

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();
            System.out.println("Added Clicked");

            addButton.relocate(625,5);
            noTaskLabel.relocate(390,70);

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000),noTaskLabel);

            addButton.setOpacity(0);
            noTaskLabel.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();

            try {
                AnchorPane formPane =
                        FXMLLoader.load(getClass().getResource("addItemForm.fxml"));


                System.out.println("AddItemController "+getUserId());

                //AddItemFormController addItemFormController = new AddItemFormController();
                //addItemFormController.setUserId(1);

                AddItemController.userId = getUserId();

                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAncherPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
    public void setUserId(int uid){
        this.userId = uid;
    }
    public void setUserId(int uid, String fname, String lname){
        String fullName = "";
        this.userId = uid;
        this.fName = fname;
        this.lName = lname;

        fullName=this.fName+" "+this.lName;

        System.out.println(this.userId+" - "+fullName);

        lblName.setText(fullName);

    }
    public int getUserId(){
        return userId;
    }

}