package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.Review;
import th.mfu.domain.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  User findByName(String name);
}
