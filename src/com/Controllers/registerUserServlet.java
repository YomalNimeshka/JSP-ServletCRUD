package com.Controllers;

import com.dao.accountDao;
import com.model.accountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("registerUserServlet")
public class registerUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        accountModel model = new accountModel();

        String userName = request.getParameter("user-name");
        int mobileNumber = Integer.parseInt((request.getParameter("mobile-number")));
        String gender = request.getParameter("user-gender");
        String password = request.getParameter("user-password");

        model.setUserName(userName);
        model.setMobileNumber(mobileNumber);
        model.setGender(gender);
        model.setPassword(password);

        accountDao dao = new accountDao();
        try {
            dao.registerUser(model);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("view/login.jsp");
        rd.forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
