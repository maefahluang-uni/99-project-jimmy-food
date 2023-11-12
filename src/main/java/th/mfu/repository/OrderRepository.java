package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllById(Long id);

}
