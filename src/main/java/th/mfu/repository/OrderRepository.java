package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Cart;
import th.mfu.domain.Order;

public interface OrderRepository extends CrudRepository<Cart,Long>{

    void save(Order order);
 
} 