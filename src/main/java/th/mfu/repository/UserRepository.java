package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.*;

public interface UserRepository extends CrudRepository<User, Long>{

    void save(org.apache.catalina.User user);
    
}