package br.com.mercadolivre.desafiospring.exception;

public class UserIsNotASellerException extends Exception {

    public UserIsNotASellerException(String message) {
        super(message);
    }

    public UserIsNotASellerException() {
    }
}
