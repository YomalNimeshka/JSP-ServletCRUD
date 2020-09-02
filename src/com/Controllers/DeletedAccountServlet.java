package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "deletedAccountServlet")
public class DeletedAccountServlet extends HttpServlet {
    public String currentAccountUserName;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountModel model =new AccountModel();
        AccountDao dao = new AccountDao();
        currentAccountUserName = request.getParameter("userName");

        model.setUserName(currentAccountUserName);
        dao.deleteAccount(model);
        response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1&sortButton=user_id");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
