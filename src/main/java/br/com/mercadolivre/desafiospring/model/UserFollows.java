package br.com.mercadolivre.desafiospring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_follows")
public class UserFollows implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_following", referencedColumnName = "id")
    private User userFollowing;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_user", referencedColumnName = "id")
    private User followedUser;

    public UserFollows() {
    }

    public User getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(User userFollowing) {
        this.userFollowing = userFollowing;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollows that = (UserFollows) o;
        return Objects.equals(userFollowing, that.userFollowing) &&
                Objects.equals(followedUser, that.followedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFollowing, followedUser);
    }
}
