package com.Controllers;

import com.dao.AccountDao;

import com.model.AccountModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


//@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private String DOWNLOAD_FILE_NAME = "Report.pdf";

    private String FILE_TYPE = "application/pdf";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateReport(request,response);
        response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1&sortButton=user_id");





    }

    private void generateReport(HttpServletRequest request, HttpServletResponse response){
        List<AccountModel> dataList;
        String reportPath;
        OutputStream outputStream;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        AccountDao jasperData;
        String logoFilePath;
        Map reportParameters;

        try {
            reportPath = request.getServletContext().getRealPath("WEB-INF/reports") + "/report1.jrxml";

            reportParameters = new HashMap();
            reportParameters.put("userName", "User Name");

            jasperDesign = JRXmlLoader.load(reportPath);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            jasperData = new AccountDao();
            dataList = jasperData.downloadPDF();
            reportSource = new JRBeanCollectionDataSource(dataList, false);

            byte[] byteStream;
            byteStream = JasperRunManager.runReportToPdf(jasperReport,reportParameters, reportSource);

            outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=" +DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(byteStream.length);
            outputStream.write(byteStream,0, byteStream.length);
            System.out.println("PDF Created");
            outputStream.close();



        }catch (JRException e){
            Logger.getLogger(DownloadServlet.class.getName()).log(Level.SEVERE, null, e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
