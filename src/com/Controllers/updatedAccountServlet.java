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

//@WebServlet(name = "updatedAccountServlet")
public class updatedAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountModel model = new accountModel();

        String username = request.getParameter("user-name");
        int mobileNumber = Integer.parseInt(request.getParameter("mobile-number"));
        String gender = request.getParameter("user-gender");

        model.setUserName(username);
        model.setMobileNumber(mobileNumber);
        model.setGender(gender);

        accountDao dao = new accountDao();
        dao.updateAccount(model);

        response.sendRedirect(request.getContextPath()+ "/dashboardServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
