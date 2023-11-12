package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllById(Long id);

}
