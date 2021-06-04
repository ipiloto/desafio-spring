package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.dto.ResponseDTO;
import br.com.mercadolivre.desafiospring.dto.UserPostsDTO;
import br.com.mercadolivre.desafiospring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @RequestMapping("/newpost")
    public ResponseEntity newPost(@RequestBody PostDTO productPostDTO){
        try {
            productService.newPost(productPostDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    @RequestMapping("/posts/{userPostId}/list")
    public ResponseEntity listUserPosts(@PathVariable Long userPostId){
        try {
            UserPostsDTO userPostsDTO = productService.listUserPosts(userPostId);
            return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping
    @RequestMapping("/followed/{userId}/list")
    public ResponseEntity listFollowedUsersPostsLastTwoWeeks(@PathVariable Long userId, @RequestParam(defaultValue = "date_desc") String[] order){
        try {
            UserPostsDTO userPostsDTO = productService.listFollowedUsersPostsLastTwoWeeks(userId, order);
            return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        }
    }

}
