package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.*;

public interface OrderRepository extends CrudRepository<Order, Long>{

    Object findAllById(Long id);
    
}