package br.com.ada.americanas.adamon.exception;

public class NegocioException extends RuntimeException{

    private String message;

    public NegocioException(String message){
        super(message);
        this.message = message;
    }
}
