package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.Dlc;
import com.udesc.padroesdeprojeto.gamelog.model.User;

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
    protected Dlc createGames() {
        return new Dlc();
    }

    @Override
    public Dlc setGames(String name, String released, String developer, String description, String CoverImage) {
        Dlc dlc = this.createGames();

        dlc.setName(name);
        dlc.setReleased(released);
        dlc.setDeveloper(developer);
        dlc.setDescription(description);
        dlc.setCoverImage(createGames().getCoverImage());

        return dlc;
    }
}
