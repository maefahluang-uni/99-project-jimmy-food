package th.mfu.repository;

import java.util.List;
import th.mfu.domain.*;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    
}