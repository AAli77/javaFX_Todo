package com.example.finalproject_todo.model;

public class User {
    private String firstName;
    private String lastName;
    private String uesrName;
    private String password;
    private String location;
    private String gender;

    public User() {
    }

    public User(String firstName, String lastName, String uesrName, String password, String location, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uesrName = uesrName;
        this.password = password;
        this.location = location;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUesrName() {
        return uesrName;
    }

    public void setUesrName(String uesrName) {
        this.uesrName = uesrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}