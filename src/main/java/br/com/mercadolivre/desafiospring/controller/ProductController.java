package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.dto.UserPostsDTO;
import br.com.mercadolivre.desafiospring.exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.exception.UserNotFoundException;
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
    public ResponseEntity newPost(@RequestBody PostDTO productPostDTO) throws UserIsNotASellerException, UserNotFoundException {
        productService.newPost(productPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping
    @RequestMapping("/newpromopost")
    public ResponseEntity newPromoPost(@RequestBody PostDTO productPostDTO) throws UserIsNotASellerException, UserNotFoundException {
        return this.newPost(productPostDTO);
    }

    @GetMapping
    @RequestMapping("/{userPostId}/countPromo")
    public ResponseEntity countPromoProducts(@PathVariable Long userPostId) throws UserIsNotASellerException, UserNotFoundException {
        UserPostsDTO userPostsDTO = productService.countPromoProducts(userPostId);
        return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
    }

    @GetMapping
    @RequestMapping("/{userPostId}/list")
    public ResponseEntity listPromoProducts(@PathVariable Long userPostId) throws UserIsNotASellerException, UserNotFoundException {
        UserPostsDTO userPostsDTO = productService.listPromoProducts(userPostId);
        return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
    }

    @GetMapping
    @RequestMapping("/posts/{userPostId}/list")
    public ResponseEntity listUserPosts(@PathVariable Long userPostId) throws UserIsNotASellerException, UserNotFoundException {
        UserPostsDTO userPostsDTO = productService.listUserPosts(userPostId);
        return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
    }

    @GetMapping
    @RequestMapping("/followed/{userId}/list")
    public ResponseEntity listFollowedUsersPostsLastTwoWeeks(@PathVariable Long userId, @RequestParam(defaultValue = "date_desc") String[] order) throws UserIsNotASellerException, UserNotFoundException {
        UserPostsDTO userPostsDTO = productService.listFollowedUsersPostsLastTwoWeeks(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(userPostsDTO);
    }

}
