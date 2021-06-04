package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.Exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.Exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.dto.*;
import br.com.mercadolivre.desafiospring.mappers.ProductMapper;
import br.com.mercadolivre.desafiospring.model.Post;
import br.com.mercadolivre.desafiospring.model.Product;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.PostRepository;
import br.com.mercadolivre.desafiospring.repository.ProductRepository;
import br.com.mercadolivre.desafiospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    public void newPost(PostDTO productPostDTO) throws UserNotFoundException, UserIsNotASellerException {
        User user = userService.findValidatingUser(productPostDTO.getUserId(), true);

        Post post = ProductMapper.postDTOtoPost(productPostDTO, user);

        if(post.getDetail().getId() == null || !productRepository.existsById(post.getDetail().getId())) post.getDetail().setId(null);
        productRepository.save(post.getDetail());

        if(post.getId() == null || !postRepository.existsById(post.getId())) post.setId(null);
        postRepository.save(post);
    }

    public UserPostsDTO listUserPosts(Long userPostId) throws UserNotFoundException, UserIsNotASellerException {
        User user = userService.findValidatingUser(userPostId, true);
        return ProductMapper.userPostsToUserPostsDTO(user);
    }

    public UserPostsDTO listFollowedUsersPostsLastTwoWeeks(Long userId) throws UserNotFoundException, UserIsNotASellerException {
        User user = userService.findValidatingUser(userId, true);

        return ProductMapper.userPostsToUserPostsDTO(user);
    }

}
