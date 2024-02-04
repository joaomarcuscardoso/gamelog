package com.udesc.padroesdeprojeto.gamelog.command;

import java.util.ArrayList;
import java.util.List;

public class Invoker {

    private static Invoker instance;

    private final List<Command> commandList = new ArrayList<>();

    private Invoker(){}

    public static Invoker getInstance(){
        if(instance == null){
            instance = new Invoker();
        }
        return instance;
    }

    public void addCommandEmail(Command command){
        commandList.add(command);
    }

    public void executeCommandsEmail(){
        for(Command command : commandList){
            command.execute();
        }
        commandList.clear();
    }
}
