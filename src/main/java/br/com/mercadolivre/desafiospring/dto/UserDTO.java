package br.com.mercadolivre.desafiospring.dto;

public class UserDTO extends ResponseDTO {

    private Long id;
    private String name;
    private Boolean seller;

    public UserDTO() {
    }

    public UserDTO(String name, Boolean seller) {
        this.name = name;
        this.seller = seller;
    }

    public UserDTO(Long id, String name, Boolean seller) {
        this.id = id;
        this.name = name;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }
}
