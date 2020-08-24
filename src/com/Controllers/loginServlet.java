package com.Controllers;

import com.dao.accountDao;
import com.model.accountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountModel model = new accountModel();

        String userName = request.getParameter("user-name");
        String password = request.getParameter("user-password");

        model.setUserName(userName);
        model.setPassword(password);

        accountDao dao = new accountDao();
        try {
            int isConnected = dao.loginUser(model);

            if (isConnected==1){
                //user is been logged in
                request.setAttribute("username", userName);
                response.sendRedirect(request.getContextPath()+ "/dashboardServlet");
                /*RequestDispatcher rd = request.getRequestDispatcher("view/dashboard.jsp");
                rd.forward(request,response);*/

            }else if (isConnected==0){
                //user cannot login
                RequestDispatcher rd = request.getRequestDispatcher("view/loginWrong.jsp");
                rd.forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
