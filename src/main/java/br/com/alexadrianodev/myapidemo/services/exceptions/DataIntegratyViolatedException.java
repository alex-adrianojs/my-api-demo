package br.com.alexadrianodev.myapidemo.services.exceptions;

public class DataIntegratyViolatedException extends RuntimeException{

    public DataIntegratyViolatedException(String message) {

        super(message);
    }
}
