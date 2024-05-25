package com.two4h.two4h.shops;


import com.two4h.two4h.products.Product;
import com.two4h.two4h.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ShopsRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByName(String name);
    List<Shop> findAllBiIsActive(boolean isActive);
}
