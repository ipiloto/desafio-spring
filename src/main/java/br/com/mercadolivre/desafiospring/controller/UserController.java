package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.ResponseDTO;
import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersDTO;
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
    public ResponseEntity follow(@PathVariable Long userId, @PathVariable Long userIdToFollow ){
        try {
            userService.follow(userId, userIdToFollow);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping
    @RequestMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unFollow(@PathVariable Long userId, @PathVariable Long userIdToUnfollow ){
        try {
            userService.unfollow(userId, userIdToUnfollow);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/count")
    public ResponseEntity countFollowers(@PathVariable Long userId){
        try {
            UserFollowersDTO responseDTO = userService.countFollowers(userId);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    @RequestMapping("/{userId}/followers/list")
    public ResponseEntity listFollowers(@PathVariable Long userId, @RequestParam(defaultValue = "name_asc") String[] order){
        try {
            UserFollowersDTO responseDTO = userService.listUserFollowers(userId, order);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    @RequestMapping("/{userId}/followed/list")
    public ResponseEntity listFollowedUsers(@PathVariable Long userId, @RequestParam(defaultValue = "name_asc") String[] order){
        try {
            UserFollowersDTO responseDTO = userService.listFollowedUsers(userId, order);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @PutMapping
    @RequestMapping("/create")
    public ResponseEntity createUsers(@RequestBody List<UserDTO> usersToCreate){
        try {
            Iterable<UserDTO> responseDTO = userService.createUsers(usersToCreate);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    @RequestMapping("/list")
    public ResponseEntity listUsers(){
        try {
            Iterable<UserDTO> responseDTO = userService.listUsers();
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping
    @RequestMapping("/{userId}/makeSellerProfile")
    public ResponseEntity makeSellerProfile(@PathVariable Long userId) {
        try {
            userService.makeSellerProfile(userId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

}
