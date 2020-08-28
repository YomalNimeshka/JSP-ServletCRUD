package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDao dao = new AccountDao();
        AccountModel model = new AccountModel();
        String username = request.getParameter("search-bar");
        List<AccountModel> listOfAcc = dao.searchOption(username);
        request.setAttribute("listOfAcc",listOfAcc);
        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request,response);

        //response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1");


    }
}
