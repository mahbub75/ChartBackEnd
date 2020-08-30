package Project.User;

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
    LessonRepository lessonRepository;

    @GetMapping("admin/{adminId}/teams")
    @CrossOrigin
    public List<User> index(@PathVariable String adminId){
        return userRepository.findByCenter_id(Integer.parseInt(adminId));
    }


    @GetMapping("user/{userId}")
    @CrossOrigin
    public User getUserById(@PathVariable int userId){
        return userRepository.findOne(userId);
    }
    @DeleteMapping("user/{userId}")
    @CrossOrigin
    public void deleteUser(@PathVariable int userId){
        userRepository.delete(userId);
    }

    @PostMapping("team")
    @CrossOrigin
    public void create( @RequestParam("lessonId") String lessonId,@RequestBody Map<String, String> body){
        String name = body.get("name");
        String roll = body.get("roll");
        String password = body.get("password");
        Lesson lesson = lessonRepository.findOne(Integer.parseInt(lessonId));
        String members = body.get("members");
        int center_id = Integer.parseInt(body.get("center_id"));

        userRepository.save(new User(name,roll,password,lesson,members, center_id));
    }

//    @PostMapping("admin")
//    public void create(@RequestBody Map<String, String> body){
//        String name = body.get("name");
//        String password = body.get("password");
//        String FirstLastName = body.get("first_last_name");
//        userRepository.save(new User(name,password,FirstLastName));
//    }


}
