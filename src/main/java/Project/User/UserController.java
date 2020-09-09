package Project.User;

import Project.ExceptionHandler.CustomException;
import Project.Lesson.Lesson;
import Project.Lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("admin/{adminId}/teams")
    @CrossOrigin
    public List<User> index(@PathVariable String adminId, @RequestParam("lesson_id") int lessonId) {
        Lesson lesson = lessonRepository.findOne(lessonId);
        return userRepository.findByCenterIdAndLesson(adminId, lesson);
    }


//    @GetMapping("user/{userId}")
//    @CrossOrigin
//    public User getUserById(@PathVariable int userId) {
//        return userRepository.findOne(userId);
//    }

    @DeleteMapping("user/{userId}")
    @CrossOrigin
    public void deleteUser(@PathVariable int userId) {
        userRepository.delete(userId);
    }

    @PostMapping("team")
    @CrossOrigin
    public void create(@RequestParam("lesson_id") int lessonId, @RequestBody Map<String, String> body)throws CustomException {
        String name = body.get("name");
        String roll = body.get("roll");
        String password = body.get("password");
        Lesson lesson = lessonRepository.findOne(lessonId);
        String members = body.get("members");
        String center_id = body.get("centerId");
        User user = new User(name, roll, password, lesson, members, center_id);
        Boolean o = userRepository.existsByName(name);
        if(o){
            throw new CustomException("نام گروه باید یکتا باشد");
        }
        userRepository.save(user);
    }

//    @PostMapping("admin")
//    public void create(@RequestBody Map<String, String> body){
//        String name = body.get("name");
//        String password = body.get("password");
//        String FirstLastName = body.get("first_last_name");
//        userRepository.save(new User(name,password,FirstLastName));
//    }


}
