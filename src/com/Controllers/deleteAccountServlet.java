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

//@WebServlet(name = "deleteAccountServlet")
public class deleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountModel model =new accountModel();
        accountDao dao = new accountDao();

        String username = request.getParameter("userName");

        model.setUserName(username);

        accountModel account = dao.editAccountDetails(model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete.jsp");
        request.setAttribute("account", account);
        requestDispatcher.forward(request,response);
        dao.deleteAccount(model);
        //response.sendRedirect(request.getContextPath()+"/dashboardServlet");
    }
}
