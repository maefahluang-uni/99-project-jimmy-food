package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Object findAllByRestaurantId(Long id);
   
}
