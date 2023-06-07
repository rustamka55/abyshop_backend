package kz.iitu.abyshop.repository;

import kz.iitu.abyshop.model.Cart;
import kz.iitu.abyshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
