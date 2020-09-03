package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "deleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    public String currentAccountUserName;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountModel model =new AccountModel();
        AccountDao dao = new AccountDao();

        currentAccountUserName = request.getParameter("userName");

        model.setUserName(currentAccountUserName);

        //this to display the account details before deleting the account
        AccountModel account = dao.editAccountDetails(model);
        request.setAttribute("account", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("delete.jsp");
        requestDispatcher.forward(request,response);
/*        dao.deleteAccount(model);*/


        /*response.sendRedirect(request.getContextPath()+"/dashboardServlet?pageid=1");*/
    }
}
