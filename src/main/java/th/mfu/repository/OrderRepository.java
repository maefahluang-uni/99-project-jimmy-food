package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

    Object findAllById(Long id);
    
}