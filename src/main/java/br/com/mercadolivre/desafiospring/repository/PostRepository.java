package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.model.Post;
import br.com.mercadolivre.desafiospring.model.Product;
import br.com.mercadolivre.desafiospring.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN UserFollows uf ON p.user = uf.followedUser WHERE uf.userFollowing.id = ?1 AND p.date BETWEEN ?2 AND ?3 ORDER BY p.date DESC")
    List<Post> findAllPostsByUserFollowedUsersAndDateBetween(Long userId, Date startDate, Date endDate);

}
