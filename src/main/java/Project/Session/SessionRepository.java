package Project.Session;
import Project.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
   List <Session> findByUser_id(int userId);
   Session findByTopicAndUser(String topic, User user);
   Boolean existsByTopicAndUser(String topic, User user);
}

