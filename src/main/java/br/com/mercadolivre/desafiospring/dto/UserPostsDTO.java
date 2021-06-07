package br.com.mercadolivre.desafiospring.dto;

import java.util.List;

public class UserPostsDTO extends ResponseDTO{

    private Long userId;
    private String userName;
    private List<PostDTO> posts;
    private Integer promoproducts_count;

    public UserPostsDTO() {
    }

    public UserPostsDTO(Long userId, String name, List<PostDTO> posts) {
        this.userId = userId;
        this.userName = name;
        this.posts = posts;
    }

    public UserPostsDTO(Long userId, String userName, Integer promoproducts_count) {
        this.userId = userId;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public Integer getPromoproducts_count() { return promoproducts_count; }

    public void setPromoproducts_count(Integer promoproducts_count) { this.promoproducts_count = promoproducts_count; }
}
