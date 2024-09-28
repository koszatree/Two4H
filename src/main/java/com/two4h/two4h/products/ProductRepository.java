package com.two4h.two4h.products;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductName(String productName);
    List<Product> findAllByIsActive(boolean isActive);
    @Query("SELECT p.image FROM Product p WHERE p.id = :id")
    String findOneImageById(@Param("id") int id);
    boolean existsByProductName(String name);
    List<Product> findAllByShopId(int shopId);
}
