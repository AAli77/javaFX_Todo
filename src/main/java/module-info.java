module com.example.finalproject_todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.finalproject_todo to javafx.fxml;
    exports com.example.finalproject_todo;
    exports com.example.finalproject_todo.controller;
    opens com.example.finalproject_todo.controller to javafx.fxml;
    exports com.example.finalproject_todo.Animations;
    opens com.example.finalproject_todo.Animations to javafx.fxml;
}