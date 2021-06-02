package br.com.mercadolivre.desafiospring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_follows")
public class UserFollows implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_followed", referencedColumnName = "id")
    private User userFollow;

    public UserFollows() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserFollow() {
        return userFollow;
    }

    public void setUserFollow(User userFollow) {
        this.userFollow = userFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollows that = (UserFollows) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(userFollow, that.userFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userFollow);
    }
}
