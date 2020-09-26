package Project.User;

import Project.Lesson.Lesson;
import Project.Session.Session;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String password;
    private String name;
    private String centerId;
    private String members;
    private String roll;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Session> sessions = new HashSet<Session>();


    public User() {
    }

    public User(String name,String roll,String password, Lesson lesson) {
        this.setName(name);
        this.setPassword(password);
        this.setLesson(lesson);
        this.setRoll(roll);
    }
    public User(String name,String roll,String password, Lesson lesson,String members,String centerId) {
        this.setName(name);
        this.setPassword(password);
        this.setLesson(lesson);
        this.setRoll(roll);
        this.setCenterId(centerId);
        this.setMembers(members);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() { return lesson; }

    public String getRoll() { return roll; }

    public void setRoll(String roll) { this.roll = roll; }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) { this.centerId = centerId; }

    public String getMembers() { return members; }

    public void setMembers(String members) { this.members = members; }
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
