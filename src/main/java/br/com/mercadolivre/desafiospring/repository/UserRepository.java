package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT COUNT(user_followed) FROM UserFollows WHERE user_followed = :userId")
    long countUsersFollowingByUsersFollowingId(Long userId);

    //Esse aqui é automático porém ficou pouco otimizado pois utilizou outer join
    //long countUsersFollowingByUsersFollowingId(Long userId);

}
