package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable Long userId, @PathVariable Long userIdToFollow ){
        return userService.follow(userId, userIdToFollow) ? null : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/count")
    public ResponseEntity<UserFollowersDTO> countFollowers(@PathVariable Long userId){
        UserFollowersDTO responseDTO = userService.countFollowers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersDTO> listFollowers(@PathVariable Long userId){
        UserFollowersDTO responseDTO = userService.listUserFollowers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    @RequestMapping("/{userId}/followed/list")
    public ResponseEntity<UserFollowersDTO> listFollowedUsers(@PathVariable Long userId){
        UserFollowersDTO responseDTO = userService.listFollowedUsers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping
    @RequestMapping("/populate")
    public Iterable<User> populateUsersTest(){
        return userService.populateUsersTest();
    }
}
