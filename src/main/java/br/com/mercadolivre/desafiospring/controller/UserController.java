package br.com.mercadolivre.desafiospring.controller;


import br.com.mercadolivre.desafiospring.dto.ResponseDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import br.com.mercadolivre.desafiospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable Long userId, @PathVariable Long userIdToFollow ){
        return userService.follow(userId, userIdToFollow) ? null : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/count")
    public ResponseEntity<ResponseDTO<UserFollowersDTO>> followersCount(@PathVariable Long userId){
        ResponseDTO<UserFollowersDTO> responseDTO = new ResponseDTO<>(userService.followersCount(userId));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping
    @RequestMapping("/populate")
    public Iterable<User> populateUsersTest(){
        User user = new User("Teste1");
        User user2 = new User("Teste2");
        user = userRepository.save(user);
        user2 = userRepository.save(user2);
        user.getUsersFollowing().add(user2);
        userRepository.save(user);

        return userRepository.findAll();
    }
}
