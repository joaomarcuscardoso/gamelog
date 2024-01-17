package com.udesc.padroesdeprojeto.gamelog.facade;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.udesc.padroesdeprojeto.gamelog.model.Game;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFGenerator {
    public byte[] saveDataPDF(List<Game> datas) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            int i = 0;
            for (Game game : datas) {
                i++;
                document.add(new Paragraph("Game " + i));
                String message = "ID: " + game.getId()+
                        "\nNome: " + game.getName() +
                        "\nDescrição: " + game.getDescription()
                        ;

                Paragraph paragraph = new Paragraph(message);

                document.add(paragraph);
                document.add(new Paragraph("---------------------------------------------"));
            }
            document.add(new Paragraph("\n"));
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
