package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet("registerUserServlet")
public class RegisterUserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        accountModel model = new accountModel();

        /*        if (intValidation(true, request)){*/

        String userName = request.getParameter("user-name");
        String mobileNumber = request.getParameter("mobile-number");
        String gender = request.getParameter("user-gender");
        String password = request.getParameter("user-password");

        //backend validation for password
        Pattern passPattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,}");
        Matcher patternMatcher = passPattern.matcher(password);
        boolean match = patternMatcher.matches();

        //validation for mobile number
        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher mobilePatternMatcher = numberPattern.matcher(mobileNumber);
        boolean mobileMatch = mobilePatternMatcher.matches();


        //validation for all the user inputs for the registration.
        if (userName == null || mobileNumber == null || gender == null) {
            RequestDispatcher rd = request.getRequestDispatcher("registerError.jsp");
            rd.forward(request, response);
        } else if (!match == true) {
            RequestDispatcher rd = request.getRequestDispatcher("registerError.jsp");
            rd.forward(request, response);
        } else if (!mobileMatch == true) {
            RequestDispatcher rd = request.getRequestDispatcher("registerError.jsp");
            rd.forward(request, response);
        } else {
            AccountModel model = new AccountModel(userName, mobileNumber, gender, password);

            AccountDao dao = new AccountDao();
            try {
                int userExist = dao.checkUsernameAvailabilty(model);
                if (userExist > 0) {
                    System.out.println("User already exist in db");
                    RequestDispatcher rd = request.getRequestDispatcher("registerError.jsp");
                    rd.forward(request, response);
                } else {
                    dao.registerUser(model);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
