package br.com.mercadolivre.desafiospring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "detail")
    private Product detail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

    public Post() {
    }

    public Post(Long id, User user, Product detail, Date date, Integer category, Double price, Boolean hasPromo, Double discount) {
        this.id = id;
        this.user = user;
        this.detail = detail;
        setDate(date);
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) this.date = new Date(); else this.date = date;
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

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
