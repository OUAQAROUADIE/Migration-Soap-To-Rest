package com.studentmanagment.exception;


public class NoSuchProductException extends Exception{

    private static final long serialVersionUID = 1L;

    public NoSuchProductException(String message) {
        super(message);
    }
}
