package com.udesc.padroesdeprojeto.gamelog.Facade;


import com.udesc.padroesdeprojeto.gamelog.model.Game;

import java.util.List;

public interface FileGenerator {
    public byte[] saveFile(String type, List<Game> datas);
    public byte[] saveDataPDF(List<Game> datas);
    public byte[] saveDataCSV(List<Game> datas);
}
