package com.example.finalproject_todo.Database;

import com.example.finalproject_todo.model.Task;
import com.example.finalproject_todo.model.User;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{

    static private Connection dbConnection;
    static private JdbcPreparedStatement jdbcPreparedStatement;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+dbHost+":"
                +dbPort+"/"
                +dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser, dbPass);

        return dbConnection;

    }

    //Write
    //    public void signUpUser(String firstName, String lastName, String username,
    //                                  String password, String location, String gender) throws SQLException, ClassNotFoundException {
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO "+ Const.USERS_TABLE + "("+Const.USERS_FIRSTNAME+","+
                Const.USERS_LASTNAME+","+
                Const.USERS_USERNAME+","+
                Const.USERS_PASSWORD+","+
                Const.USERS_LOCATION+","+
                Const.USERS_GENDER+")"+
                "VALUES(?,?,?,?,?,?)";

        //System.out.println(insert);

        jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(insert);
        //jdbcPreparedStatement = (JdbcPreparedStatement)  dbConnection.prepareStatement(insert);

        jdbcPreparedStatement.setString(1,user.getFirstName());
        jdbcPreparedStatement.setString(2,user.getLastName());
        jdbcPreparedStatement.setString(3,user.getUesrName());
        jdbcPreparedStatement.setString(4,user.getPassword());
        jdbcPreparedStatement.setString(5,user.getLocation());
        jdbcPreparedStatement.setString(6,user.getGender());

        jdbcPreparedStatement.executeUpdate();

    }

    public void insertTask(Task task) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO "+ Const.TASKS_TABLE + "("+Const.TASKS_USERID+","+
                Const.TASKS_DATECREADTED+","+
                Const.TASK_TASK+","+
                Const.TASKS_DESCRIPTION+") "+
                "VALUES(?,?,?,?)";


        jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(insert);
        //jdbcPreparedStatement = (JdbcPreparedStatement)  dbConnection.prepareStatement(insert);

        jdbcPreparedStatement.setInt(1,task.getUserId());
        jdbcPreparedStatement.setTimestamp(2,task.getDateCreated());
        jdbcPreparedStatement.setString(3,task.getTask());
        jdbcPreparedStatement.setString(4,task.getDescription());

        System.out.println(insert);

        jdbcPreparedStatement.executeUpdate();

    }





    //Read
    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;

        if(!user.getUesrName().equals("") || !user.getPassword().equals("")){
            String query = "SELECT * FROM "+Const.USERS_TABLE + " WHERE "+
                            Const.USERS_USERNAME+"=?"+" AND "+
                            Const.USERS_PASSWORD+"=?";
            jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(query);
            jdbcPreparedStatement.setString(1,user.getUesrName());
            jdbcPreparedStatement.setString(2,user.getPassword());
            resultSet = jdbcPreparedStatement.executeQuery();


        }else {
            System.out.println("Invalid User.....!");

        }

        return resultSet;
    }

    public int getTaskCount(int userId){

        String query = "SELECT count(*) FROM "+Const.TASKS_TABLE + " WHERE "+
                Const.TASKS_USERID+"=?";

        try {
            jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(query);
            jdbcPreparedStatement.setInt(1,userId);
            ResultSet resultSet =   jdbcPreparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public ResultSet getTasksListByUser(int userid){

        ResultSet resultTasks = null;

        String query = "SELECT * FROM "+Const.TASKS_TABLE + " WHERE "+
                Const.TASKS_USERID+"=?";

        try {
            jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(query);
            jdbcPreparedStatement.setInt(1, userid);

            resultTasks = jdbcPreparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultTasks;
    }

    //Update

    //Delete
    public void deleteTask(int userId, int taskid){
        String query = "Delete from "+Const.TASK_TASK+" where "+
                Const.TASKS_USERID+"=?"+" And "+Const.TASKS_TASKID+"=?";

        try {
            jdbcPreparedStatement = (JdbcPreparedStatement) getDbConnection().prepareStatement(query);
            jdbcPreparedStatement.setInt(1, userId );
            jdbcPreparedStatement.setInt(2, taskid);
            jdbcPreparedStatement.executeQuery();
            jdbcPreparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
