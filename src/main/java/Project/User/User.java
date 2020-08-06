package Project.User;

import Project.Lesson.Lesson;
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
    private String first_last_name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Lesson> lessons = new HashSet<Lesson>();

    public User() {
    }

    public User(String name, String password, String first_last_name) {
        this.setName(name);
        this.setName(password);
        this.setFirstLastName(first_last_name);
    }

    public User(int id, String name, String password, String first_last_name) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setFirstLastName(first_last_name);
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

    public String getFirstLastName() {
        return first_last_name;
    }

    public void setFirstLastName(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}