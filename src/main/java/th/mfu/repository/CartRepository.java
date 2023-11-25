package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
  
    Object findAllById(Long id);
}
