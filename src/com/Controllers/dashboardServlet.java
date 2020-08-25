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
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "dashboardServlet")
public class dashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountDao dao = new accountDao();

        HttpSession session = request.getSession(false);
        if (session!=null){
            try {
                String accountName = (String)session.getAttribute("accountName");
                System.out.println(accountName);
                List<accountModel> listOfAcc = dao.displayTable();
                request.setAttribute("listOfAcc", listOfAcc);


                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request,response);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("login has to be done first");
            request.getRequestDispatcher("login.jsp").include(request,response);
        }


    }
}
