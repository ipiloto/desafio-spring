package br.com.mercadolivre.desafiospring.mappers;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.dto.ProductDTO;
import br.com.mercadolivre.desafiospring.dto.UserPostsDTO;
import br.com.mercadolivre.desafiospring.model.Post;
import br.com.mercadolivre.desafiospring.model.Product;
import br.com.mercadolivre.desafiospring.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductMapper {

    public static Post postDTOtoPost(PostDTO productPostDTO, User user){
        Product product = productDTOtoProduct(productPostDTO.getDetail());

        return new Post(productPostDTO.getId_post(), user, product, productPostDTO.getDate(), productPostDTO.getCategory(),
                productPostDTO.getPrice(), null, null );
    }

    public static Product productDTOtoProduct(ProductDTO productDTO){
        return new Product(productDTO.getProduct_id(), productDTO.getProductName(), productDTO.getType(),
                productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }

    public static PostDTO postToPostDTO(Post post){
        return new PostDTO(post.getUser().getId(), post.getId(), post.getDate(), productToProductDTO(post.getDetail()), post.getCategory(), post.getPrice());
    }

    public static List<PostDTO> postsToPostDTOList(List<Post> posts){
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(post -> postDTOList.add(postToPostDTO(post)));
        return postDTOList;
    }

    public static ProductDTO productToProductDTO(Product product){
        return new ProductDTO(product.getId(), product.getName(), product.getType(), product.getBrand(), product.getColor(), product.getNotes());
    }

    public static UserPostsDTO userPostsToUserPostsDTO(Long userId, List<Post> posts) {
        return new UserPostsDTO(userId, postsToPostDTOList(posts));
    }

}
