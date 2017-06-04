package entity;

/**
 * Created by Ace on 2017/5/31.
 */
public class TeacherEntity {
    private long id;
    private String name;
    private String gender;

    public TeacherEntity() {
    }

    public TeacherEntity(long id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
