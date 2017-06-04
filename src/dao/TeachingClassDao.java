package dao;

import entity.TeachingClassEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/3.
 */
public class TeachingClassDao extends BaseDao{

    public List<TeachingClassEntity> getList(String sql){
        List<TeachingClassEntity> list = new ArrayList<TeachingClassEntity>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new TeachingClassEntity(rs.getLong(1), rs.getLong(2),
                        rs.getLong(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return list;
    }

    public TeachingClassEntity getByID(String id){
        TeachingClassEntity teachingClassEntity=new TeachingClassEntity();
        String sql="select couId,teaId from t_teachingclass where id=?";
        ResultSet rs=this.executeQuery(sql,id);
        try {
            if(rs.next()){
                teachingClassEntity= new TeachingClassEntity(rs.getLong(1),rs.getLong(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return teachingClassEntity;
    } public int insert(TeachingClassEntity teachingClassEntity){
        String sql="CALL teachingclass(?,?);";
        return this.executeUpdate(sql,teachingClassEntity.getCouId(),teachingClassEntity.getTeaId());
    }
}
