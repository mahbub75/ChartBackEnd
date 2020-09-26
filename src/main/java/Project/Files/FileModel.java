package Project.Files;

import Project.Session.Session;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String uniqueName;
    @ManyToOne
    @JoinColumn(name = "sessionId")
    private Session session;


    public FileModel (){

    }
    public FileModel(String name, String uniqueName, Session session) {
        this.setName(name);
        this.setSession(session);
        this.setUniqueName(uniqueName);
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

    public void setName(String topic) {
        this.name = topic;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public Session getSession() {
        return session;
    }

}
