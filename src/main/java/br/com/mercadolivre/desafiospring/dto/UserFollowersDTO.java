package br.com.mercadolivre.desafiospring.dto;

import java.util.List;

public class UserFollowersDTO extends ResponseDTO{

    private Long userId;
    private String userName;
    private Long followers_count;
    private List<UserFollowersDTO> followers;
    private List<UserFollowersDTO> followed;

    public UserFollowersDTO() {
    }

    public UserFollowersDTO(Long userId, String userName, Long followers_count) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public UserFollowersDTO(String errors) {
        super(errors);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Long followers_count) {
        this.followers_count = followers_count;
    }

    public List<UserFollowersDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserFollowersDTO> followers) {
        this.followers = followers;
    }

    public List<UserFollowersDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserFollowersDTO> followed) {
        this.followed = followed;
    }
}
