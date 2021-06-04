package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.exception.UserIsNotASellerException;
import br.com.mercadolivre.desafiospring.exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.dto.*;
import br.com.mercadolivre.desafiospring.mappers.ProductMapper;
import br.com.mercadolivre.desafiospring.model.Post;
import br.com.mercadolivre.desafiospring.model.User;
import br.com.mercadolivre.desafiospring.repository.PostRepository;
import br.com.mercadolivre.desafiospring.repository.ProductRepository;
import br.com.mercadolivre.desafiospring.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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
        return ProductMapper.userPostsToUserPostsDTO(user.getId(), user.getPosts());
    }

    public UserPostsDTO listFollowedUsersPostsLastTwoWeeks(Long userId, String[] order) throws UserNotFoundException, UserIsNotASellerException {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, -14);
        Date twoWeeksBefore = calendar.getTime();

        User user = userService.findValidatingUser(userId, false);

        List<Post> postsLastTwoWeeks = postRepository.findAllPostsByUserFollowedUsersAndDateBetween(user.getId(), twoWeeksBefore, today,
                SortUtil.sortStringToSort(order));

        return ProductMapper.userPostsToUserPostsDTO(user.getId(), postsLastTwoWeeks);
    }

}
