package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Order;
import th.mfu.domain.OrderItem;

public interface OrderRepository extends CrudRepository<OrderItem,Long>{

    void save(Order order);
 
} 