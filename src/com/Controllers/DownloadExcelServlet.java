package com.Controllers;

import com.dao.AccountDao;
import com.model.AccountModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(name = "DownloadExcelServlet")
public class DownloadExcelServlet extends HttpServlet {
    private String DOWNLOAD_FILE_NAME = "ReportExcel.xlsx";
    private String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateExcel(request,response);
        response.sendRedirect(request.getContextPath()+ "/dashboardServlet?pageid=1&sortButton=user_id");
    }

    public void generateExcel(HttpServletRequest request, HttpServletResponse response){
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

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,reportParameters, reportSource);

            /*byte[] byteStream;
            byteStream = JasperRunManager.runReportToPdf(jasperReport,reportParameters, reportSource);*/

            outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=" +DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(4096);

            JRXlsxExporter exporterXLS = new JRXlsxExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, DOWNLOAD_FILE_NAME);
            exporterXLS.exportReport();


            System.out.println("Xlsx Created");
            outputStream.close();



        }catch (JRException e){
            Logger.getLogger(DownloadExcelServlet.class.getName()).log(Level.SEVERE, null, e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
