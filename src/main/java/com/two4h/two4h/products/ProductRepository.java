package com.two4h.two4h.products;


import com.two4h.two4h.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.OrientationRequested;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);
    List<Product> findAllByIsActive(boolean isActive);
}
