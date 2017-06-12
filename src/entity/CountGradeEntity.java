package entity;

/**
 * Created by Ace on 2017/6/8.
 */
public class CountGradeEntity {
    private float grade;
    private String stuName;

    public CountGradeEntity() {
    }

    public CountGradeEntity(float grade, String stuName) {
        this.grade = grade;
        this.stuName = stuName;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

}
