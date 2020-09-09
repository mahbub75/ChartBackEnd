package Project.Session;

import Project.Files.FileModel;
import Project.Lesson.Lesson;
import Project.Lesson.LessonRepository;
import Project.User.User;
import Project.User.UserRepository;
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
    UserRepository userRepository ;

    @GetMapping("team/{teamId}/sessions")
    @CrossOrigin
    List<Session> getSessionList(@PathVariable int teamId){
return sessionRepository.findByUser_id(teamId);
    }

    @GetMapping("session/{sessionId}")
    @CrossOrigin
    Session getSession(@PathVariable int sessionId){
return sessionRepository.findOne(sessionId);
    }

    @PostMapping("session")
    @CrossOrigin
    public void create(@RequestBody Session session, @RequestParam("teamId") int teamId){
        User team = userRepository.findOne(teamId);
         sessionRepository.save(new Session(session.getTopic(),team));
    }
//    @PutMapping("session/{sessionId}")
//    @CrossOrigin
//    public void put(@RequestBody Session session, @PathVariable String sessionId){
//        int lessonId= Integer.parseInt( sessionId);
//        Lesson lesson = lessonRepository.findOne(lessonId);
//        sessionRepository.save(new Session(session.getTopic()));
//    }
}
