package kz.iitu.abyshop.repository;

import kz.iitu.abyshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Void deleteProductById(Integer id);
}
