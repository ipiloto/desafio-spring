package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.mappers.UserMapper;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import br.com.mercadolivre.desafiospring.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    public UserFollowersDTO listUserFollowers(Long userId, String[] order) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, true);
        List<User> followers = userRepository.findFollowingUsersByFollowedUserId(user.getId(), SortUtil.sortStringToSort(order));
        UserFollowersDTO userFollowersDTO = UserMapper.userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowers(UserMapper.usersToUserFollowersDTOList(followers));
        return userFollowersDTO;
    }

    public UserFollowersDTO listFollowedUsers(Long userId, String[] order) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, false);
        List<User> followedUsers = userRepository.findFollowedUsersByUserFollowingId(user.getId(), SortUtil.sortStringToSort(order));
        UserFollowersDTO userFollowersDTO = UserMapper.userToUserFollowersDTO(user, null);
        userFollowersDTO.setFollowed(UserMapper.usersToUserFollowersDTOList(followedUsers));
        return userFollowersDTO;
    }

    public User findValidatingUser(Long userId, boolean needToBeSeller) throws UserNotFoundException, UserIsNotASellerException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The User informed does not exist."));
        if(needToBeSeller && !user.isSeller()) throw new UserIsNotASellerException("The user is not a seller.");
        return user;
    }

    public void makeSellerProfile(Long userId) throws UserIsNotASellerException, UserNotFoundException {
        User user = findValidatingUser(userId, false);
        user.setSeller(true);
        userRepository.save(user);
    }

    public Iterable<UserDTO> createUsers(List<UserDTO> usersToCreate) {
        userRepository.saveAll(UserMapper.usersDTOtoUsersList(usersToCreate));
        return UserMapper.usersToUsersDTOList((List<User>) userRepository.findAll());
    }

    public Iterable<UserDTO> listUsers() {
        return UserMapper.usersToUsersDTOList((List<User>) userRepository.findAll());
    }
}
