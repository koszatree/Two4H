package com.two4h.two4h.orders;


import com.two4h.two4h.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
