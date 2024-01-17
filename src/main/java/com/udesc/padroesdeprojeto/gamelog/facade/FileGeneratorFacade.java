package com.udesc.padroesdeprojeto.gamelog.facade;

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
            PDFGenerator pdfGen = new PDFGenerator();
            return pdfGen.saveDataPDF(datas);
        } else if (type.equals("csv")) {
            CSVGenerator csvGen = new CSVGenerator();
            return csvGen.saveDataCSV(datas);
        }

        return null;
    }
}
