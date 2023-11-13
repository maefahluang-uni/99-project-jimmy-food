package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
  
    Object findAllById(Long id);
}
