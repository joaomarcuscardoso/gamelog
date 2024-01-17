package com.udesc.padroesdeprojeto.gamelog.facade;

import com.opencsv.CSVWriter;
import com.udesc.padroesdeprojeto.gamelog.model.Game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVGenerator {
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
