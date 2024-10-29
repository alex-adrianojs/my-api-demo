package br.com.alexadrianodev.myapidemo.services.exceptions;

public class DataIntegratyViolatedExcpetion extends RuntimeException{

    public DataIntegratyViolatedExcpetion(String message) {

        super(message);
    }
}
