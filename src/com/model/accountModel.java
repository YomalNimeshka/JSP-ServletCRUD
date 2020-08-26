package com.model;

public class accountModel {
    private String userName;
    private String mobileNumber;
    private String gender;
    private String password;

    public accountModel() {
    }

    public accountModel(String userName, String mobileNumber, String gender) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }

    public accountModel(String userName, String mobileNumber, String gender, String password) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "accountModel{" +
                "userName='" + userName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
