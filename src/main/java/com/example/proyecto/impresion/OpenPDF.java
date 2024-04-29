package com.example.proyecto.impresion;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.proyecto.models.PlayersModel;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

public class OpenPDF {

    private void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + fileName;

        response.setHeader(headerKey, headerValue);

    }

    public void exportToPdf(List<PlayersModel> playerList, HttpServletResponse response) throws DocumentException, IOException{

        setResponseHeader(response, "application/pdf", ".pdf", "Player's List_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph paragraph = new Paragraph("List of Players", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writePlayerheader(table);
        writePlayerData(table, playerList);

        document.add(table);
        document.close();

    }

    private void writePlayerheader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Player's Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Player's Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Player's Age", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Player's Position", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Player's Squad Number", font));
        table.addCell(cell);

    }

    private void writePlayerData(PdfPTable table, List<PlayersModel> playerList){
        for(PlayersModel p : playerList){
            table.addCell(p.getPlayerName());
            table.addCell(p.getPlayerLastName());
            table.addCell(String.valueOf(p.getPlayerAge()));
            table.addCell(p.getPlayerPosition());
            table.addCell(String.valueOf(p.getPlayerSquadNumber()));
        }
    }

}
