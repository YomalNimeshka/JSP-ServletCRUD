package com.dao;

import com.db.DbConnection;
import com.model.AccountModel;
import com.util.HashingFunction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    static Connection connection;
    static int isLoggedIn;
     int noOfRecords;

     public int checkUsernameAvailabilty(AccountModel model){
         String sqlToCheckAvailabilty = "select count(*) from onlineaccount where user_name = ?";
         int n =0;
         try {
             connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlToCheckAvailabilty);
             preparedStatement.setString(1, model.getUserName());
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()){
                 n =resultSet.getInt(1);
                 return n;
             }

         }catch (Exception e){
             e.printStackTrace();
         }
         return n;

     }


    public void registerUser(AccountModel model) throws SQLException, ClassNotFoundException {
        String sql = "insert into onlineaccount (user_name, mobile_number, gender, password) values (?, ?, ?, ?)";


        try {
            connection = DbConnection.getConnection();
            HashingFunction hashPassword = new HashingFunction();

            //hashing the password
            String userPasssword = model.getPassword();
            String hashedPassword = hashPassword.convertByteToString(hashPassword.createHash(userPasssword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, model.getMobileNumber());
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

    public int loginUser(AccountModel model)throws SQLException, ClassNotFoundException{
        String sql = "select * from onlineaccount where user_name = ? and password = ?";
        try{

            connection = DbConnection.getConnection();
            HashingFunction hashPassword = new HashingFunction();

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

    public List<AccountModel> displayTable() throws  SQLException, ClassNotFoundException{
        List<AccountModel> listOfAcc = new ArrayList<>();
        String sql = "select * from onlineaccount";


        try{
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString("user_name");
                String mobileNumber = (resultSet.getString("mobile_number"));
                String gender = resultSet.getString("gender");
                listOfAcc.add(new AccountModel(username,mobileNumber,gender));
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return listOfAcc;
    }

    public AccountModel editAccountDetails(AccountModel model){
        String sql = "select * from onlineaccount where user_name = ?";

        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username = resultSet.getString("user_name");
                String mobileNumber = resultSet.getString("mobile_number");
                String gender = resultSet.getString("gender");
                String password = resultSet.getString("password");
                model = new AccountModel(username,mobileNumber,gender, password);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public void updateAccount(AccountModel model){
        String sql = "update onlineaccount set mobile_number = ?, gender = ? where user_name = ?";

        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getMobileNumber());
            preparedStatement.setString(2, model.getGender());
            preparedStatement.setString(3, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("Data has been updated ");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("data could not be updated");
        }
    }


    public void deleteAccount(AccountModel model){
        String sql = "delete from onlineaccount where user_name =?";
        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.executeUpdate();
            System.out.println("account deleted");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("could not be deleted");
        }
    }

    public List<AccountModel> pagination(int start, int total, String usernameSort, String accountName){
        String sql = "select  * from onlineaccount where user_name != '"+accountName+"' order by "+usernameSort+ " desc limit "+(start-1)+", "+total;
        List<AccountModel> accounts = new ArrayList<AccountModel>();

        try {
            connection = DbConnection.getConnection();
            PreparedStatement Statement = connection.prepareStatement(sql);
            ResultSet resultSet = Statement.executeQuery();
                while (resultSet.next() ){
                    String username = resultSet.getString("user_name");
                    String mobileNumber =(resultSet.getString("mobile_number"));
                    String gender =(resultSet.getString("gender"));
                    accounts.add(new AccountModel(username,mobileNumber,gender));
                }
            connection.close();
            System.out.println(usernameSort);

        }catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }

    public int NoOfRecords(){
        try {
            connection= DbConnection.getConnection();
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery("select count(*) from onlineaccount");
            if (resultSet.next()){
                this.noOfRecords = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<AccountModel> searchOption(String search, String accountName){
         String sql = "select * from onlineaccount where user_name = ? or mobile_number = ? ";
         List<AccountModel> searchedValues = new ArrayList<>();
         AccountModel model = new AccountModel();

        try {
            connection= DbConnection.getConnection();
            //Statement Statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            //ResultSet resultSet = Statement.executeQuery(sql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                String username = resultSet1.getString("user_name");
                String mobileNumber =(resultSet1.getString("mobile_number"));
                String gender =(resultSet1.getString("gender"));
                searchedValues.add(new AccountModel(username,mobileNumber,gender));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchedValues;
    }

    public List downloadPDF(){
         String sql = "select * from onlineaccount";
         AccountModel objBean;
         List<AccountModel> dataList = new ArrayList<AccountModel>(0);



        try {
            connection= DbConnection.getConnection();
            //Statement Statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objBean = new AccountModel();
                String userName = (resultSet.getString("user_name"));
                String mobileNumber = (resultSet.getString("mobile_number"));
                String gender = (resultSet.getString("gender"));

                dataList.add(new AccountModel(userName, mobileNumber, gender));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
       // System.out.println(dataList);
        return dataList;
    }
}
