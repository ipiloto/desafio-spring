package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.dto.UserPostsDTO;
import br.com.mercadolivre.desafiospring.exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.mappers.ProductMapper;
import br.com.mercadolivre.desafiospring.mappers.UserMapper;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void follow(Long userId, Long userIdToFollow) throws UserIsNotASellerException, UserNotFoundException {
        if (userId.equals(userIdToFollow)) return;
        User user = findValidatingUser(userId, false);
        User userToFollow = findValidatingUser(userIdToFollow, true);
        if (user.getFollowedUsers().contains(userToFollow)) return;
        user.getFollowedUsers().add(userToFollow);
        userRepository.save(user);
    }

    public void unfollow(Long userId, Long userIdToFollow) throws UserIsNotASellerException, UserNotFoundException {
        if (userId.equals(userIdToFollow)) return;
        User user = findValidatingUser(userId, false);
        User userToUnfollow = findValidatingUser(userIdToFollow, true);
        if (!user.getFollowedUsers().contains(userToUnfollow)) return;
        user.getFollowedUsers().remove(userToUnfollow);
        userRepository.save(user);
    }

    public UserFollowersDTO countFollowers(Long userId) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, true);
        long followersCount = userRepository.countFollowersByUserFollowed(user.getId());
        return UserMapper.userToUserFollowersDTO(user, followersCount);
    }

    public UserFollowersDTO listUserFollowers(Long userId) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, true);
        List<User> followers = userRepository.findAllUserFollowsByFollowedUsersId(user.getId());
        UserFollowersDTO userFollowersDTO = UserMapper.userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowers(UserMapper.usersToUserFollowersDTOList(followers));
        return userFollowersDTO;
    }

    public UserFollowersDTO listFollowedUsers(Long userId) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, false);
        UserFollowersDTO userFollowersDTO = UserMapper.userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowed(UserMapper.usersToUserFollowersDTOList(user.getFollowedUsers()));
        return userFollowersDTO;
    }

    public User findValidatingUser(Long userId, boolean needToBeSeller) throws UserNotFoundException, UserIsNotASellerException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The User informed does not exist."));
        if(needToBeSeller && !user.isSeller()) throw new UserIsNotASellerException("The user is not a seller.");
        return user;
    }

}
