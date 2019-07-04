package com.gabryelrock.core.temaFinal.ErrorHandler;

public class ErrorWriteFile extends RuntimeException {

    public ErrorWriteFile(String error) {
        super(error);
    }
}
