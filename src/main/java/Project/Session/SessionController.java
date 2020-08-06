package Project.Session;

import Project.Files.FileModel;
import Project.Lesson.Lesson;
import Project.Lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SessionController {
    @Autowired
    SessionRepository sessionRepository ;
    @Autowired
    LessonRepository lessonRepository ;

    @GetMapping("lesson/{lessonId}/sessions")
    @CrossOrigin
    List<Session> getSessionList(@PathVariable int lessonId){
return sessionRepository.findByLesson_id(lessonId);
    }

    @GetMapping("session/{sessionId}")
    @CrossOrigin
    Session getSession(@PathVariable int sessionId){
return sessionRepository.findOne(sessionId);
    }

    @PostMapping("session")
    @CrossOrigin
    public void create(@RequestBody Session session, @RequestParam("lessonId") String lessonId){
        Lesson lesson = lessonRepository.findOne(Integer.parseInt(lessonId));
         sessionRepository.save(new Session(session.getTopic(),lesson));
    }
    @PutMapping("session/{sessionId}")
    @CrossOrigin
    public void put(@RequestBody Session session, @PathVariable String sessionId){
        int lessonId= Integer.parseInt( sessionId);
        Lesson lesson = lessonRepository.findOne(lessonId);
        sessionRepository.save(new Session(session.getTopic()));
    }
}
