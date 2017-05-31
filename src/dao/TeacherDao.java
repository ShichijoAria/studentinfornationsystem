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
            if(search.getGender().equals("0")){
                sql+=" where gender='"+search.getGender()+"' and";
            }else if(search.getGender().equals("1")){
                sql+=" where gender='"+search.getGender()+"' and";
            }else {
                sql+=" where ";
            }
            sql += " id like '%";
            sql +=search.getId()==null?"":search.getId();
            sql +="%' and name like '%";
            sql +=search.getName()==null?"":search.getName();
            sql +="%'";
        }
        List<TeacherEntity> list=new ArrayList<TeacherEntity>();
        ResultSet rs=this.executeQuery(sql);
        try{
            while(rs.next()){
                list.add(new TeacherEntity(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return list;
    }

}
