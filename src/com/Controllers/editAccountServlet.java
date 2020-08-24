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

//@WebServlet(name = "editAccountServlet")
public class editAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountModel model = new accountModel();

        String username = request.getParameter("userName");
        model.setUserName(username);
        System.out.println(username);

        accountDao dao = new accountDao();
        accountModel accountDetails = dao.editAccountDetails(model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("account", accountDetails);
        requestDispatcher.forward(request,response);
    }
}
