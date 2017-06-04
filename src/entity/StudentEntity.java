package entity;

import java.util.Date;

/**
 * Created by Ace on 2017/6/1.
 */
public class StudentEntity {
    private long id;
    private String name;
    private String gender;
    private String faculty;
    private String grade;
    private String itemClass;
    private Date timeOfEn;
    private String identity;

    public StudentEntity() {
    }

    public StudentEntity(long id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public StudentEntity(long id, String name, String gender, String faculty, String grade, String itemClass, Date timeOfEn, String identity) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.faculty = faculty;
        this.grade = grade;
        this.itemClass = itemClass;
        this.timeOfEn = timeOfEn;
        this.identity = identity;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public Date getTimeOfEn() {
        return timeOfEn;
    }

    public void setTimeOfEn(Date timeOfEn) {
        this.timeOfEn = timeOfEn;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
