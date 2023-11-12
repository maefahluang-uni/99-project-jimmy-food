package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    // เมทอด save ควรรับพารามิเตอร์เป็น th.mfu.domain.User ไม่ใช่ org.apache.catalina.User
    @Override
    <S extends User> S save(S user);

}
