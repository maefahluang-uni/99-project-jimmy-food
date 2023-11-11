package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.*;

public interface ItemRepository extends CrudRepository<Item, Long>{

    Object findAllById(Long id);
    
}