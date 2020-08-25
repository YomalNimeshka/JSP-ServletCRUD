package com.dao;

import com.db.dbConnection;
import com.model.accountModel;
import com.util.hashingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class accountDao {
    static Connection connection;
    static int isLoggedIn;

    public void registerUser(accountModel model) throws SQLException, ClassNotFoundException {
        String sql = "insert into onlineaccount (user_name, mobile_number, gender, password) values (?, ?, ?, ?)";
        try {
            connection = dbConnection.getConnection();
            hashingFunction hashPassword = new hashingFunction();

            //hashing the password
            String userPasssword = model.getPassword();
            String hashedPassword = hashPassword.convertByteToString(hashPassword.createHash(userPasssword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setInt(2, model.getMobileNumber());
            preparedStatement.setString(3, model.getGender());
            preparedStatement.setString(4, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println( "Data has been send to db");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Data hasn't been sent to db");
        }
    }

    public int loginUser(accountModel model)throws SQLException, ClassNotFoundException{
        String sql = "select * from onlineaccount where user_name = ? and password = ?";
        try{

            connection = dbConnection.getConnection();
            hashingFunction hashPassword = new hashingFunction();

            //hashing the password
            String userPassword = model.getPassword();
            String hashedPassword = hashPassword.convertByteToString(hashPassword.createHash(userPassword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("User can login");
                isLoggedIn=1;

            }else{
                System.out.println("User cannot login");
                isLoggedIn=0;
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Data hasn't been delivered to db to check login");
        }
        return isLoggedIn;
    }

    public List<accountModel> displayTable() throws  SQLException, ClassNotFoundException{
        List<accountModel> listOfAcc = new ArrayList<>();
        String sql = "select * from onlineaccount";


        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString("user_name");
                int mobileNumber = (resultSet.getInt("mobile_number"));
                String gender = resultSet.getString("gender");
                listOfAcc.add(new accountModel(username,mobileNumber,gender));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listOfAcc;
    }

    public accountModel editAccountDetails(accountModel model){
        String sql = "select * from onlineaccount where user_name = ?";

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString("user_name");
                int mobileNumber = resultSet.getInt("mobile_number");
                String gender = resultSet.getString("gender");
                String password = resultSet.getString("password");
                model = new accountModel(username,mobileNumber,gender, password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public void updateAccount(accountModel model){
        String sql = "update onlineaccount set mobile_number = ?, gender = ? where user_name = ?";

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, model.getMobileNumber());
            preparedStatement.setString(2, model.getGender());
            preparedStatement.setString(3, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("Data has been updated ");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("data could not be updated");
        }
    }

    public void updateAccountWithMN(accountModel model){
        String sql ="update onlineaccount set mobile_number = ? where user_name = ?";

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, model.getMobileNumber());
            preparedStatement.setString(2, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("Data has been updated ");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("data could not be updated");
        }
    }

    public void updateAccountWithGender(accountModel model){
        String sql = "update onlineaccount set  gender = ? where user_name = ?";

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getGender());
            preparedStatement.setString(2, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("Data has been updated ");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("data could not be updated");
        }
    }

    public void deleteAccount(accountModel model){
        String sql = "delete from onlineaccount where user_name =?";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("account deleted");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("could not be deleted");
        }
    }
}
