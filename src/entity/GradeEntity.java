package entity;

/**
 * Created by Ace on 2017/6/6.
 */
public class GradeEntity {
    String id;
    String teaName;
    String couName;
    String stuName;
    float grade;
    long classId;
    long stuId;

    public long getClassId() {
        return classId;
    }

    public GradeEntity() {
    }

    public GradeEntity(String teaName, String couName, String stuName, float grade) {
        this.teaName = teaName;
        this.couName = couName;
        this.stuName = stuName;
        this.grade = grade;
    }

    public GradeEntity(String id, String teaName, String couName, String stuName) {
        this.id = id;
        this.teaName = teaName;
        this.couName = couName;
        this.stuName = stuName;
        this.grade = grade;
    }

    public GradeEntity(long stuid, long classid){
        this.classId = classid;
        this.stuId = stuid;
    }

    public GradeEntity(String id, long classid, long stuid) {
        this.id = id;
        this.classId = classid;
        this.stuId = stuid;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
