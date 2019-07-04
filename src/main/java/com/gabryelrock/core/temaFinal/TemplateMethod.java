package com.gabryelrock.core.temaFinal;

import java.util.List;

public abstract class TemplateMethod {

    final void execute(){

        if(fileExist()){
            readFile();
            processFile();
            saveFile();
        }else{
            System.out.println("File does not exist!");
        }
    }

    abstract boolean fileExist();

    abstract List<String> readFile();

    abstract void processFile();

    abstract boolean saveFile();
}
