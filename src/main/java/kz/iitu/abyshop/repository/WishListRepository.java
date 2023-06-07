package kz.iitu.abyshop.repository;

import kz.iitu.abyshop.model.User;
import kz.iitu.abyshop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

    Void deleteWishListById(Integer id);
}
