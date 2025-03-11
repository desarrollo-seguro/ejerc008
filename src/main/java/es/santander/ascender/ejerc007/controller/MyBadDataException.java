package es.santander.ascender.ejerc007.controller;

public class MyBadDataException extends RuntimeException {

    private long registro;

    public MyBadDataException() {

    }

    public MyBadDataException(String message, long registro) {
        super(message);
        this.registro = registro;
    }

    public MyBadDataException(String message, Throwable cause, long registro) {
        super(message, cause);
        this.registro = registro;

    }


    public long getRegistro() {
        return registro;
    }

}
