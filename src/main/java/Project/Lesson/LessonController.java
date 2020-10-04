package Project.Lesson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("lesson/{lessonId}")
    @CrossOrigin
    public Lesson getLesson(@PathVariable int lessonId){
        return this.lessonRepository.getOne(lessonId);
    }
}
