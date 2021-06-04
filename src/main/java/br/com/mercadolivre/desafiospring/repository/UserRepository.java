package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT COUNT(followed_user) FROM UserFollows WHERE followed_user = :followedUserId")
    long countFollowersByUserFollowed(Long followedUserId);

    @Query("SELECT followedUser FROM UserFollows WHERE user_following = :userFollowingId")
    List<User> findFollowedUsersByUserFollowingId(Long userFollowingId, Sort sort);

    @Query("SELECT userFollowing FROM UserFollows WHERE followed_user = :followedUserId")
    List<User> findFollowingUsersByFollowedUserId(Long followedUserId, Sort sort);



}
