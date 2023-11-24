package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByPassword(String password);

}
