package dao;

import entity.TeacherEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/5/31.
 */
public class TeacherDao extends BaseDao{

    public List<TeacherEntity> getList(TeacherEntity search){
        String sql="select *from t_teacher";
        if(search!=null) {
            if(search.getGender()!=null) {
                if (search.getGender().equals("0")) {
                    sql += " where gender='" + search.getGender() + "' and";
                } else if (search.getGender().equals("1")) {
                    sql += " where gender='" + search.getGender() + "' and";
                }
            }else {
                sql+=" where ";
            }
            sql += " id like '%";
            sql +=search.getId()<0?"":search.getId();
            sql +="%' and name like '%";
            sql +=search.getName()==null?"":search.getName();
            sql +="%'";
        }
        List<TeacherEntity> list=new ArrayList<TeacherEntity>();
        ResultSet rs=this.executeQuery(sql);
        try{
            while(rs.next()){
                list.add(new TeacherEntity(rs.getLong(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return list;
    }

    public TeacherEntity getByID(String id){
        TeacherEntity teacherEntity=new TeacherEntity();
        String sql="select *from t_teacher where id=?";
        ResultSet rs=this.executeQuery(sql,id);
        try {
            if(rs.next()){
                teacherEntity= new TeacherEntity(rs.getLong(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return teacherEntity;
    }

    public int update(TeacherEntity teacherEntity,String id){
        String sql="update t_teacher set id=? ,name=?,gender=? where id=?";
        return this.executeUpdate(sql,teacherEntity.getId(),teacherEntity.getName(),teacherEntity.getGender(),id);
    }

    public int insert(TeacherEntity teacherEntity){
        String sql="insert into t_teacher(id,name,gender) values(?,?,?)";
        return this.executeUpdate(sql,teacherEntity.getId(),teacherEntity.getName(),teacherEntity.getGender());
    }
}
