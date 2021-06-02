package br.com.mercadolivre.desafiospring.dto;

public class ResponseDTO<T> {
    private T data;
    private String erros;

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO(String erros) {
        this.erros = erros;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErros() {
        return erros;
    }

    public void setErros(String erros) {
        this.erros = erros;
    }
}
