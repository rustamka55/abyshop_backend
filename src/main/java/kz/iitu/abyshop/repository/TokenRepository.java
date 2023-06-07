package kz.iitu.abyshop.repository;

import kz.iitu.abyshop.model.AuthenticationToken;
import kz.iitu.abyshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
