package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.model.Post;
import br.com.mercadolivre.desafiospring.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

}
