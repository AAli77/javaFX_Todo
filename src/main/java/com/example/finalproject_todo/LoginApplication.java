package com.example.finalproject_todo;

import com.example.finalproject_todo.Database.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/assets/test.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();


        //DatabaseHandler dbHandler = new DatabaseHandler();
        //ResultSet resultSet = dbHandler.getTasksListByUser(11);
        //while (true){
        //    try {
        //        if (!resultSet.next()) break;
        //        System.out.println("User task "+resultSet.getString("task"));
        //    } catch (SQLException e) {
        //        e.printStackTrace();
        //    }
        //}
        //System.out.println("Current taskcount for userid 1, "+ dbHandler.getTaskCount(1));

    }

    public static void main(String[] args) {
        launch();
    }
}