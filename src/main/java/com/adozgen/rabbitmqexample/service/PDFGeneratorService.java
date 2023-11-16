package com.adozgen.rabbitmqexample.service;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.lowagie.text.Document;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException {
        // HTTP response ayarları
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"200_sayfa_rapor.pdf\"");

        // PDF dokümanı oluştur
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Rapor başlığı
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);
        Paragraph headerParagraph = new Paragraph("## PDF Heading ##", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Rapor içeriği
        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

        // 200 sayfalık raporu oluştur
        for (int pageNumber = 1; pageNumber <= 200; pageNumber++) {
            document.newPage(); // Yeni bir sayfa ekleyin
            Paragraph pageHeader = new Paragraph("Sayfa " + pageNumber, fontHeader);
            pageHeader.setAlignment(Paragraph.ALIGN_CENTER);

            // Her sayfaya verileri ekleyin
            Paragraph pdfParagraph = new Paragraph("", fontParagraph);
            pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            for (int i = 0; i < 15; i++) {
                pdfParagraph.add("Row: " + i + " Time: " + dateFormatter.format(new Date()) + "\n");
            }

            document.add(pageHeader);
            document.add(pdfParagraph);
        }
        document.close();
    }
}
