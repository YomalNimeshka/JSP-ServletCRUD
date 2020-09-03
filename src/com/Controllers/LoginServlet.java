package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountModel model = new AccountModel();

        String userName = request.getParameter("user-name");
        String password = request.getParameter("user-password");

        model.setUserName(userName);
        model.setPassword(password);

        AccountDao dao = new AccountDao();
        try {
            int isConnected = dao.loginUser(model);

            if (isConnected==1){
                //user is been logged in and creating a session
                HttpSession session = request.getSession(true);

                session.setAttribute("accountName", userName);
                response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1&sortButton=user_id");
                /*RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
                rd.forward(request,response);*/

            }else if (isConnected==0){
                //user cannot login
                RequestDispatcher rd = request.getRequestDispatcher("loginWrong.jsp");
                rd.include(request,response);
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
