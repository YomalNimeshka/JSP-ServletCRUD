package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "editAccountServlet")
public class EditAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountModel model = new AccountModel();

        String username = request.getParameter("userName");
        model.setUserName(username);
        //System.out.println(username);

        //this to display the details of the username to the edit page
        AccountDao dao = new AccountDao();
        AccountModel accountDetails = dao.editAccountDetails(model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("account", accountDetails);
        requestDispatcher.forward(request,response);
    }
}
