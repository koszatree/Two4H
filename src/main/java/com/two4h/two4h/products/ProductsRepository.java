package com.two4h.two4h.products;


import com.two4h.two4h.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
