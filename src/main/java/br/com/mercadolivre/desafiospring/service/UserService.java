package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean follow(Long userId, Long userIdToFollow) {
        if (userId.equals(userIdToFollow)) return true;
        try {
            if(userRepository.existsById(userId) && userRepository.existsById(userIdToFollow)){
                User user = userRepository.findById(userId).orElseThrow();
                User userToFollow = userRepository.findById(userIdToFollow).orElseThrow();
                if (user.getUsersFollowing().contains(userToFollow)) return true;
                user.getUsersFollowing().add(userToFollow);
                userRepository.save(user);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public UserFollowersDTO followersCount(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        long followersCount = userRepository.countUsersFollowingByUsersFollowingId(user.getId());
        return new UserFollowersDTO(user.getId(), user.getName(), followersCount);
    }
}
