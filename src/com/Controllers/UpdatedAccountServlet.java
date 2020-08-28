package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(name = "updatedAccountServlet")
public class UpdatedAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountModel model = new AccountModel();

        String username = request.getParameter("user-name");
        String mobileNumber = request.getParameter("mobile-number");
        String gender = request.getParameter("user-gender");

        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher mobilePatternMatcher = numberPattern.matcher(mobileNumber);
        boolean mobileMatch = mobilePatternMatcher.matches();

        if (mobileNumber==null){
            response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1");
        }else if (!mobileMatch==true){
            response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1");
        }else{
            model.setUserName(username);
            model.setMobileNumber(mobileNumber);
            model.setGender(gender);

            AccountDao dao = new AccountDao();
            dao.updateAccount(model);
//        dao.updateAccountWithMN(model);
//        dao.updateAccountWithGender(model);

            response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
