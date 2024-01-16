package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.Dlc;

public class DlcFactory extends GamesFactory{

    private static DlcFactory instance;

    private DlcFactory(){}

    public static DlcFactory getInstance(){
        if(instance == null){
            instance = new DlcFactory();
        }
        return instance;
    }
    @Override
    public Dlc createGames() {
        return new Dlc();
    }
}
