package com.gabryelrock.core.temaFinal.ErrorHandler;

public class ErrorReadFile extends RuntimeException{

    public ErrorReadFile(String error) {
        super(error);
    }
}
