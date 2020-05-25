package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);

    List<User> findByUsernameContainingOrNameContaining(String username, String name);

}
