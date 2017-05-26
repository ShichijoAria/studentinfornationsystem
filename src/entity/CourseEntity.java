package entity;

/**
 * Created by Ace on 2017/5/26.
 */
public class CourseEntity {
    private String id;
    private String name;

    public CourseEntity() {
    }

    public CourseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
