package com.adozgen.rabbitmqexample.controller;

import com.adozgen.rabbitmqexample.handler.ReportGenerator;
import com.adozgen.rabbitmqexample.service.PdfData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class PDFExportController {
    @Autowired
    ReportGenerator reportGenerator;

    @GetMapping("/openpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String filePath = "pdf_" + currentDateTime + ".pdf";
        String content = "Bu bir PDF raporudur.";
        PdfData pdfData = new PdfData();
        pdfData.setContent(content);
        pdfData.setFilePath(filePath);
        reportGenerator.publish(pdfData);
    }
}
