package br.com.mercadolivre.desafiospring.service;

import br.com.mercadolivre.desafiospring.Exception.UserNotFoundException;
import br.com.mercadolivre.desafiospring.dto.*;
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
    UserRepository userRepository;

    public void newPost(PostDTO productPostDTO) throws UserNotFoundException {
        User user = userRepository.findById(productPostDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("Usuário informado não existe"));

        Post post = postDTOtoPost(productPostDTO, user);

        if(post.getDetail().getId() == null || !productRepository.existsById(post.getDetail().getId())) post.getDetail().setId(null);
        productRepository.save(post.getDetail());

        if(post.getId() == null || !postRepository.existsById(post.getId())) post.setId(null);
        postRepository.save(post);
    }

    private Post postDTOtoPost(PostDTO productPostDTO, User user){
        Product product = productDTOtoProduct(productPostDTO.getDetail());

        return new Post(productPostDTO.getId_post(), user, product, productPostDTO.getDate(), productPostDTO.getCategory(),
                productPostDTO.getPrice(), null, null );
    }

    private Product productDTOtoProduct(ProductDTO productDTO){
        return new Product(productDTO.getProduct_id(), productDTO.getProductName(), productDTO.getType(),
                productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }

    private PostDTO postToPostDTO(Post post){
        return new PostDTO(post.getUser().getId(), post.getId(), post.getDate(), productToProductDTO(post.getDetail()), post.getCategory(), post.getPrice());
    }

    private List<PostDTO> postsToPostDTOList(List<Post> posts){
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(post -> postDTOList.add(postToPostDTO(post)));
        return postDTOList;
    }

    private ProductDTO productToProductDTO(Product product){
        return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getBrand(), product.getColor(), product.getNotes());
    }

    public UserPostsDTO listFollowedUserPosts(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário informado não existe"));
        return userPostsToUserPostsDTO(user);
    }

    private UserPostsDTO userPostsToUserPostsDTO(User user) {
        return new UserPostsDTO(user.getId(), postsToPostDTOList(user.getPosts()));
    }
}
