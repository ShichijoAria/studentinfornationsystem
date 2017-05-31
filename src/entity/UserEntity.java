package entity;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserEntity {
    private String type;//用户权限
    private String id;////用户标识id
    private String name;//用户名
    private String password;//用户密码

    public UserEntity() {
    }

    public UserEntity(String id, String type, String password) {
        this.type = type;
        this.id = id;
        this.password = password;
    }

    public UserEntity(String id, String type, String name, String password) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
