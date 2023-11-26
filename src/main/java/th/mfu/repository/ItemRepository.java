package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
