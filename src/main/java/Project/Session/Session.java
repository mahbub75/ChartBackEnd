package Project.Session;

import Project.Files.FileModel;
import Project.User.User;

import javax.persistence.*;
import java.util.*;


@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String topic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    Set<FileModel> file = new HashSet<FileModel>();


    public Session() {

    }

    public Session(String topic) {
        this.setTopic(topic);
    }

    public Session(String topic, User user) {
        this.setTopic(topic);
        this.setUser(user);

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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
