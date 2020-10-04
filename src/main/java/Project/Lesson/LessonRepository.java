package Project.Lesson;
import Project.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
Lesson findByUsers(User user);
    
}
