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
    private int center_id;
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
    public User(String name,String roll,String password, Lesson lesson,String members,int center_id) {
        this.setName(name);
        this.setName(password);
        this.setLesson(lesson);
        this.setRoll(roll);
        this.setCenter_id(center_id);
        this.setMembers(roll);
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

    public int getCenter_id() {
        return center_id;
    }

    public void setCenter_id(int center_id) { this.center_id = center_id; }

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
