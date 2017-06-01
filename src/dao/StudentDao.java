package dao;

import entity.StudentEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/1.
 */
public class StudentDao extends BaseDao{

    public List<StudentEntity> getList(StudentEntity search){
        String sql="select *from t_student where id !='0'";
        if(search!=null) {
            sql += " and id like '%";
            sql +=search.getId()==null?"":search.getId();
            sql +="%' and name like '%";
            sql +=search.getName()==null?"":search.getName();
            sql +="%' and gender like '%";
            sql +=search.getGender()==null?"":search.getGender();
            sql +="%' and faculty like '%";
            sql +=search.getFaculty()==null?"":search.getFaculty();
            sql +="%'";
        }
        List<StudentEntity> list=new ArrayList<StudentEntity>();
        ResultSet rs=this.executeQuery(sql);
        try {
            while (rs.next()) {
                list.add(new StudentEntity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return list;
    }

    public StudentEntity getByID(String id){
        StudentEntity studentEntity=new StudentEntity();
        String sql="select * from t_student where id=?";
        ResultSet rs=this.executeQuery(sql,id);
        try {
            if(rs.next()){
                studentEntity=new StudentEntity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return studentEntity;
    }

    public int update(StudentEntity studentEntity,String id){
        String sql="update t_student set id=? ,name=? ,gender=? ,faculty=? ,grade=? ,class=? ,timeofen=? ,identity=? where id=?";
        return this.executeUpdate(sql,studentEntity.getId(),studentEntity.getName(),studentEntity.getGender(),studentEntity.getFaculty(),
                studentEntity.getGrade(),studentEntity.getItemClass(),studentEntity.getTimeOfEn(),studentEntity.getIdentity(),id);
    }

    public int insert(StudentEntity studentEntity){
        String sql="insert into t_student(id,name,gender,faculty,grade,class,timeofen,identity) values(?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql,studentEntity.getId(),studentEntity.getName(),studentEntity.getGender(),studentEntity.getFaculty(),
                studentEntity.getGrade(),studentEntity.getItemClass(),studentEntity.getTimeOfEn(),studentEntity.getIdentity());
    }
}
