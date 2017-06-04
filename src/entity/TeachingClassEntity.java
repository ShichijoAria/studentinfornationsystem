package entity;

/**
 * Created by Ace on 2017/6/3.
 */
public class TeachingClassEntity {
    private long id;
    private long couId;
    private String couName;
    private long teaId;
    private String teaName;

    public TeachingClassEntity() {
    }

    public TeachingClassEntity(long couId, long teaId) {
        this.couId = couId;
        this.teaId = teaId;
    }

    public TeachingClassEntity(String couName, String teaName) {
        this.couName = couName;
        this.teaName = teaName;
    }

    public TeachingClassEntity(long id, long couId, long teaId, String couName, String teaName) {
        this.id = id;
        this.couId = couId;
        this.couName = couName;
        this.teaId = teaId;
        this.teaName = teaName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCouId() {
        return couId;
    }

    public void setCouId(long couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public long getTeaId() {
        return teaId;
    }

    public void setTeaId(long teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

}
