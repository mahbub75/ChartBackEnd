package Project.User;

import Project.ExceptionHandler.CustomException;
import Project.Lesson.Lesson;
import Project.Lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

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



    @DeleteMapping("user/{userId}")
    @CrossOrigin
    public void deleteUser(@PathVariable int userId) {
        userRepository.delete(userId);
    }

    @DeleteMapping("delete-all-teams")
    @CrossOrigin
    public void deleteUser() {
        List<User> users = userRepository.findByRoll("Team");
        for (User user:users) {
            userRepository.delete(user.getId());
        }

    }

    @PostMapping("team")
    @CrossOrigin
    public void create(@RequestParam("lesson_id") int lessonId, @RequestBody User user) throws CustomException {
        Lesson lesson = lessonRepository.findOne(lessonId);
        user.setLesson(lesson);
        Boolean isExist = userRepository.existsByName(user.getName());
        if (isExist) {
            throw new CustomException("نام گروه باید یکتا باشد");
        }
        userRepository.save(user);
    }

    @PostMapping(value = "cumulative-registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public void teamsCumulativeRegistration(@RequestParam("file") MultipartFile multipartFile) throws IOException {


// convert multipartFile to File
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
// read file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 4);
            if (parts.length >= 4) {
                String adminName = parts[3];
                String studentName = parts[1];
                String teamName = parts[2];
                Boolean isExistTeam = this.userRepository.existsByName(teamName);
                Boolean isExistAdmin = this.userRepository.existsByName(adminName);
                if (isExistAdmin) {
                    if (isExistTeam) {
                        User team = userRepository.findByName(teamName);
                        team.setMembers(team.getMembers() +"-"   +studentName);
                        this.userRepository.save(team);
                    } else {
                        User admin = userRepository.findByName(adminName);
                        String adminId = String.valueOf(admin.getId());
                        Lesson lesson = lessonRepository.findByUsers(admin);
                        this.userRepository.save(new User(teamName, "Team", "123456", lesson, studentName,adminId));
                    }

                }

            }
        }
    }




@PutMapping(value="user")
@CrossOrigin
    public void editUser(@RequestBody User user){
    Boolean isExist = userRepository.existsByName(user.getName());
    if (isExist) {
        throw new CustomException("نام گروه باید یکتا باشد");
    }
        userRepository.save(user);
}
}
