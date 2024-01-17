package com.udesc.padroesdeprojeto.gamelog.Facade;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.udesc.padroesdeprojeto.gamelog.model.Game;

import java.io.*;
import java.util.*;

import java.io.IOException;
import java.util.List;

public class FileGeneratorFacade implements FileGenerator {

    public byte[] saveFile(String type, List<Game> datas) {
        if (type.equals("pdf")) {
            return this.saveDataPDF(datas);
        } else if (type.equals("csv")) {
            return this.saveDataCSV(datas);
        }

        return null;
    }

    public byte[] saveDataPDF(List<Game> datas) {
        System.out.println("aqui1");
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

    public byte[] saveDataCSV(List<Game> datas) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(baos));
            List<String[]> csvDataList = new ArrayList<>();

            csvDataList.add(new String[]{"ID", "Game", "Description"});
            for (Game data : datas) {
                String[] rowData = new String[]{
                        String.valueOf(data.getId()),
                        data.getName(),
                        data.getDescription()
                };
                csvDataList.add(rowData);
            }

            csvWriter.writeAll(csvDataList);
            csvWriter.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
