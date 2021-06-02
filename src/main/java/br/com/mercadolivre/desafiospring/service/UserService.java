package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //only for tests
    public Iterable<User> populateUsersTest() {
        User user = new User("Teste1");
        User user2 = new User("Teste2");
        user = userRepository.save(user);
        user2 = userRepository.save(user2);
        user.getFollowedUsers().add(user2);
        userRepository.save(user);
        return userRepository.findAll();
    }

    public boolean follow(Long userId, Long userIdToFollow) {
        if (userId.equals(userIdToFollow)) return true;
        try {
            if(userRepository.existsById(userId) && userRepository.existsById(userIdToFollow)){
                User user = userRepository.findById(userId).orElseThrow();
                User userToFollow = userRepository.findById(userIdToFollow).orElseThrow();
                if (user.getFollowedUsers().contains(userToFollow)) return true;
                user.getFollowedUsers().add(userToFollow);
                userRepository.save(user);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public UserFollowersDTO countFollowers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        long followersCount = userRepository.countFollowersByUserFollowed(user.getId());
        return userToUserFollowersDTO(user, followersCount);
    }

    public UserFollowersDTO listUserFollowers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        List<User> followers = userRepository.findAllUserFollowsByFollowedUsersId(user.getId());
        UserFollowersDTO userFollowersDTO = userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowers(usersToUserFollowersDTOList(followers));
        return userFollowersDTO;
    }

    public UserFollowersDTO listFollowedUsers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        UserFollowersDTO userFollowersDTO = userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowed(usersToUserFollowersDTOList(user.getFollowedUsers()));
        return userFollowersDTO;
    }

    private List<UserFollowersDTO> usersToUserFollowersDTOList(List<User> userList){
        List<UserFollowersDTO> userFollowersDTOList = new ArrayList<>();
        userList.forEach( user -> {
            userFollowersDTOList.add(userToUserFollowersDTO(user, null));
        });
        return userFollowersDTOList;
    }

    private UserFollowersDTO userToUserFollowersDTO(User user, Long followers_count){
        return new UserFollowersDTO(user.getId(), user.getName(), followers_count);
    }
}
