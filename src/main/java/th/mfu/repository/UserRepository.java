package th.mfu.repository;

import org.apache.catalina.User;

public class UserRepository {

    public void save(User user) {
    }
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
}