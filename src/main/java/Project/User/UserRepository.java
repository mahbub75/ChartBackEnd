package Project.User;

import Project.Lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByName(String name);
    User findByNameAndPassword(String name, String password);
  List<User> findByCenterIdAndLesson(String centerId, Lesson lesson);
 Boolean existsByNameAndPassword(String name, String password);
    Boolean existsByName(String name);
}
