package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
  
}
