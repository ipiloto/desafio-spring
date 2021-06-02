package br.com.mercadolivre.desafiospring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String errors;

    public ResponseDTO() {}

    public ResponseDTO(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
