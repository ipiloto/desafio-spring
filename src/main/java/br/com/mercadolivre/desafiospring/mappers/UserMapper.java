package br.com.mercadolivre.desafiospring.mappers;

import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapper {

    public static List<UserFollowersDTO> usersToUserFollowersDTOList(List<User> userList){
        List<UserFollowersDTO> userFollowersDTOList = new ArrayList<>();
        userList.forEach( user -> userFollowersDTOList.add(userToUserFollowersDTO(user, null)));
        return userFollowersDTOList;
    }

    public static UserFollowersDTO userToUserFollowersDTO(User user, Long followers_count){
        return new UserFollowersDTO(user.getId(), user.getName(), followers_count);
    }

    public static List<User> usersDTOtoUsersList(List<UserDTO> usersDTO){
        List<User> users = new ArrayList<>();
        usersDTO.forEach(userDTO -> users.add(new User(userDTO.getName(), userDTO.isSeller())));
        return users;
    }

    public static List<UserDTO> usersToUsersDTOList(List<User> users){
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(new UserDTO(user.getId(), user.getName(), user.isSeller())));
        return usersDTO;
    }

}
