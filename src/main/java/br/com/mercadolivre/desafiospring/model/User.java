package br.com.mercadolivre.desafiospring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "followedUsers", "posts"})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean isSeller;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="user_follows",
            joinColumns = @JoinColumn(name = "user_following", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "followed_user", referencedColumnName = "id"))
    private List<User> followedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(String name, boolean isSeller) {
        this.name = name;
        this.isSeller = isSeller;
    }

    public User(String name) {
        this.name = name;
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

    public List<User> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<User> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
