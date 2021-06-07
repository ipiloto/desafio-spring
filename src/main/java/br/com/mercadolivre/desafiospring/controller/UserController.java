package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
import br.com.mercadolivre.desafiospring.exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable Long userId, @PathVariable Long userIdToFollow) throws UserIsNotASellerException, UserNotFoundException {
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping
    @RequestMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unFollow(@PathVariable Long userId, @PathVariable Long userIdToUnfollow) throws UserIsNotASellerException, UserNotFoundException {
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/count")
    public ResponseEntity countFollowers(@PathVariable Long userId) throws UserIsNotASellerException, UserNotFoundException {
        UserFollowersDTO responseDTO = userService.countFollowers(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/list")
    public ResponseEntity listFollowers(@PathVariable Long userId, @RequestParam(defaultValue = "name_asc") String[] order) throws UserIsNotASellerException, UserNotFoundException {
        UserFollowersDTO responseDTO = userService.listUserFollowers(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    @RequestMapping("/{userId}/followed/list")
    public ResponseEntity listFollowedUsers(@PathVariable Long userId, @RequestParam(defaultValue = "name_asc") String[] order) throws UserIsNotASellerException, UserNotFoundException {
        UserFollowersDTO responseDTO = userService.listFollowedUsers(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping
    @RequestMapping("/create")
    public ResponseEntity createUsers(@RequestBody List<UserDTO> usersToCreate) {
        Iterable<UserDTO> responseDTO = userService.createUsers(usersToCreate);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    @RequestMapping("/list")
    public ResponseEntity listUsers() {
        Iterable<UserDTO> responseDTO = userService.listUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping
    @RequestMapping("/{userId}/makeSellerProfile")
    public ResponseEntity makeSellerProfile(@PathVariable Long userId) throws UserIsNotASellerException, UserNotFoundException {
        userService.makeSellerProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
