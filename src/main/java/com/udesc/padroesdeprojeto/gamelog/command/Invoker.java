package com.udesc.padroesdeprojeto.gamelog.command;

import java.util.ArrayList;
import java.util.List;

public class Invoker {

    private static Invoker instance;

    private final List<EmailCommand> emailCommandList = new ArrayList<>();

    private Invoker(){}

    public static Invoker getInstance(){
        if(instance == null){
            instance = new Invoker();
        }
        return instance;
    }

    public void addCommandEmail(EmailCommand emailCommand){
        emailCommandList.add(emailCommand);
    }

    public void executeCommandsEmail(){
        for(EmailCommand command : emailCommandList){
            command.execute();
        }
        emailCommandList.clear();
    }
}
