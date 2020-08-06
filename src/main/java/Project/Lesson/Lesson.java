package Project.Lesson;

import Project.Files.Pair;
import Project.Session.Session;
import Project.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String teacher_info;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    Set<Session> sessions = new HashSet<Session>();

    public Lesson() {
    }

    public Lesson(String name) {
        this.setName(name);
    }

    public Lesson(String name, String teacher_info, User user) {
        this.setName(name);
        this.setTeacherInfo(teacher_info);
        this.setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherInfo() {
        return teacher_info;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacher_info = teacherInfo;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
