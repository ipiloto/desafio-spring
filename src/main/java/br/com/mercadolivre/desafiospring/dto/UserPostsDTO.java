package br.com.mercadolivre.desafiospring.dto;

import java.util.List;

public class UserPostsDTO extends ResponseDTO{

    private Long userId;
    private List<PostDTO> posts;

    public UserPostsDTO() {
    }

    public UserPostsDTO(Long userId, List<PostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
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
}
