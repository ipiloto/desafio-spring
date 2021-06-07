package br.com.mercadolivre.desafiospring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PostDTO extends ResponseDTO {

    private Long userId;
    private Long id_post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private Double price;

    private Boolean hasPromo;
    private Double discount;

    public PostDTO() {
    }

    public PostDTO(Long userId, Long id_post, Date date, ProductDTO detail, Integer category, Double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public PostDTO(Long userId, Long id_post, Date date, ProductDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getHasPromo() { return hasPromo; }

    public void setHasPromo(Boolean hasPromo) { this.hasPromo = hasPromo; }

    public Double getDiscount() { return discount; }

    public void setDiscount(Double discount) { this.discount = discount; }
}
