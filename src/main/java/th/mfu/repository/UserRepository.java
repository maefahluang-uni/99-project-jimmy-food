package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S user);

    Object findById(Long id);

}
