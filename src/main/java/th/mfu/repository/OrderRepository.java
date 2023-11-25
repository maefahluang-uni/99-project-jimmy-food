package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Cart;
import th.mfu.domain.Order;

public interface OrderRepository extends JpaRepository<Cart,Long>{

    void save(Order order);
 
} 