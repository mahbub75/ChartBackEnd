package Project.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByNameAndPassword(String name, String password);
  List<User> findByCenter_id(int centerId);
}
