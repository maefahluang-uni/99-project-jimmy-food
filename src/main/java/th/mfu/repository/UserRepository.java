package th.mfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.mfu.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);

}
