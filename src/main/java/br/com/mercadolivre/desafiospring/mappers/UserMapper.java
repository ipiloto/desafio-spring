package br.com.mercadolivre.desafiospring.mappers;

import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapper {

    public static List<UserFollowersDTO> usersToUserFollowersDTOList(List<User> userList){
        List<UserFollowersDTO> userFollowersDTOList = new ArrayList<>();
        userList.forEach( user -> {
            userFollowersDTOList.add(userToUserFollowersDTO(user, null));
        });
        return userFollowersDTOList;
    }

    public static UserFollowersDTO userToUserFollowersDTO(User user, Long followers_count){
        return new UserFollowersDTO(user.getId(), user.getName(), followers_count);
    }
}
