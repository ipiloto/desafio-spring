package br.com.mercadolivre.desafiospring.dto;

public class UserFollowersDTO {

    private Long userId;
    private String userName;
    private long followers_count;

    public UserFollowersDTO(Long userId) {
        this.userId = userId;
    }

    public UserFollowersDTO(Long userId, String userName, long followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
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

    public long getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(long followers_count) {
        this.followers_count = followers_count;
    }
}
