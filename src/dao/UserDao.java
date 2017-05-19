package dao;

import entity.UserEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserDao extends BaseDao{
    public UserEntity login(UserEntity u){
        String sql="";
        String type=u.getType();
        if(u.getType().equals("1")) {//管理员登录
            sql = "SELECT id,name,password FROM t_administrator where id=? and password=?";
        }else if(u.getType().equals("2")){//教师登录
            sql = "SELECT id,name,password FROM t_teacher where id=? and password=?";
        }else{//学生登录
            sql = "SELECT id,name,password FROM t_student where id=? and password=?";
        }
        ResultSet rs = this.executeQuery(sql, u.getId(),u.getPassword());
        try{
            if(rs.next()){
                return new UserEntity(rs.getString(1),type,rs.getString(2),rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
