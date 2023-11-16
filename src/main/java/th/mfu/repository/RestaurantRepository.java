package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Restaurant;
import th.mfu.domain.User;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    User findAll();

    Object findById(Long id);
  
}
