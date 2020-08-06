package Project.Lesson;

import Project.Session.Session;
import Project.User.User;
import Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

@RestController
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;
    UserRepository userRepository;

    @GetMapping("user/{userId}/lessons")
    @CrossOrigin
    public List<Lesson> index(@PathVariable int userId){

        return lessonRepository.findByUser_id(userId);
    }

    @PostMapping("lesson")
    @CrossOrigin
    public void create(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String teacherInfo = body.get("teacher_info");
        int userId= Integer.parseInt( body.get("user_id"));
        User user = userRepository.findOne(userId);
        lessonRepository.save(new Lesson(name,teacherInfo,user));
    }
    @GetMapping("lesson/{lessonId}")
    @CrossOrigin
    public Lesson getLesson(@PathVariable String lessonId){
        return this.lessonRepository.getOne(Integer.parseInt(lessonId));
    }
}
