package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.*;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    
}