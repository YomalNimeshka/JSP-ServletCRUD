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
import java.util.List;

//@WebServlet(name = "dashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDao dao = new AccountDao();

        HttpSession session = request.getSession(false);
        if (session!=null){

            String accountName = (String)session.getAttribute("accountName");
            //System.out.println(accountName);

            String sortButton= request.getParameter("sortButton");
//            System.out.println(sortButton);
            String spageid=request.getParameter("pageid");
            int pageid=Integer.parseInt(spageid);

            if (sortButton.equals("user_id")){
                    int total=5;
                    String sortId = "user_id";
                    if(pageid==1){}
                    else{
                        pageid=pageid-1;
                        pageid=pageid*total+1;
                    }
                    int noOfRecords = dao.NoOfRecords();
                    int noOfPages = (int)Math.ceil(noOfRecords*1.0/total);

                    List<AccountModel> listOfAcc = dao.pagination(pageid,total,sortId,accountName);
                    request.setAttribute("listOfAcc",listOfAcc);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", pageid);
                    request.setAttribute("sortType", sortId);
                }else{

                    int total=5;
                    String userNameSort = "user_name";
                    if(pageid==1){}
                    else{
                        pageid=pageid-1;
                        pageid=pageid*total+1;
                    }
                    int noOfRecords = dao.NoOfRecords();
                    int noOfPages = (int)Math.ceil(noOfRecords*1.0/total);

                    List<AccountModel> listOfAcc = dao.pagination(pageid,total, userNameSort,accountName);
                    request.setAttribute("listOfAcc",listOfAcc);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", pageid);
                    request.setAttribute("sortType", userNameSort);
                }



                /*AccountDao dao = new AccountDao();
                AccountModel model = new AccountModel();*/
;


                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request,response);


        }else{
            System.out.println("login has to be done first");
            request.getRequestDispatcher("login.jsp").include(request,response);
        }


    }
}
