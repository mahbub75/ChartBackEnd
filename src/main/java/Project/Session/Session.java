package Project.Session;

import Project.Files.FileModel;
import Project.Lesson.Lesson;

import javax.persistence.*;
import java.util.*;


@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String topic;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    Set<FileModel> file = new HashSet<FileModel>();


    public Session() {

    }

    public Session(String topic) {
        this.setTopic(topic);
    }

    public Session(String topic, Lesson lesson) {
        this.setTopic(topic);
        this.setLesson(lesson);

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
