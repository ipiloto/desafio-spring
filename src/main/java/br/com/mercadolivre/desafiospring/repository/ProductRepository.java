package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
