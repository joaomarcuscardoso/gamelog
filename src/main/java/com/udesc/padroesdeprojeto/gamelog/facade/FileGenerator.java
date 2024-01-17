package com.udesc.padroesdeprojeto.gamelog.facade;


import com.udesc.padroesdeprojeto.gamelog.model.Game;

import java.util.List;

public interface FileGenerator {
    public byte[] saveFile(String type, List<Game> datas);
}
