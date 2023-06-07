package kz.iitu.abyshop.repository;

import kz.iitu.abyshop.model.AuthenticationToken;
import kz.iitu.abyshop.model.Category;
import kz.iitu.abyshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Void deleteCategoryById(Integer id) ;

}
