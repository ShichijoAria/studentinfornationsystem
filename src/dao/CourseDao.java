package dao;

import entity.CourseEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/5/26.
 */
public class CourseDao extends BaseDao{

    public List<CourseEntity> getList(CourseEntity search){
        String sql="select * from t_course";
        if(search!=null) {
            sql += " where id like '%";
            sql +=search.getId()==null?"":search.getId();
            sql +="%' and name like '%";
            sql +=search.getName()==null?"":search.getName();
            sql +="%'";
        }
        List<CourseEntity> list = new ArrayList<CourseEntity>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new CourseEntity(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return list;
    }

    public CourseEntity getByID(String id){
        CourseEntity courseEntity=new CourseEntity();
        String sql="select *from t_course where id=?";
        ResultSet rs=this.executeQuery(sql,id);
        try {
            if(rs.next()){
                courseEntity= new CourseEntity(rs.getString(1),rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return courseEntity;
    }

    public int update(CourseEntity courseEntity,String id){
        String sql="update t_course set id=? ,name=? where id=?";
        return this.executeUpdate(sql,courseEntity.getId(),courseEntity.getName(),id);
    }

    public int insert(CourseEntity courseEntity){
        String sql="insert into t_course(id,name) values(?,?)";
        return this.executeUpdate(sql,courseEntity.getId(),courseEntity.getName());
    }
}
