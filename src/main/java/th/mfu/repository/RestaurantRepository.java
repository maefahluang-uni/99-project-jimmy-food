package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
  
}
