package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

<<<<<<< HEAD
    Object findAllBySellerId(Long id);
=======
    Object findAllById(Long id);
>>>>>>> origin/main
   
}
