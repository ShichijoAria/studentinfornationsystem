package dao;

import entity.GradeEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/6.
 */
public class GradeDao extends BaseDao{
    public List<GradeEntity> getList(String sql,GradeEntity search){
        if(search!=null) {
            sql += " and t.name like '%";
            sql +=search.getTeaName()==null?"":search.getTeaName();
            sql +="%' and c.name like '%";
            sql +=search.getCouName()==null?"":search.getCouName();
            sql +="%' and s.name like '%";
            sql +=search.getStuName()==null?"":search.getStuName();
            sql +="%'";
        }
        List<GradeEntity> list = new ArrayList<GradeEntity>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new GradeEntity(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return list;
    }

    public GradeEntity getByID(String id){
        GradeEntity courseEntity=new GradeEntity();
        String ID[]=id.split(":");
        String sql="SELECT t.name,c.name,s.name,g.grade \n" +
                "FROM t_grade g,t_student s,t_teacher t,t_course c,t_teachingclass tc \n" +
                "WHERE g.classid=tc.id AND tc.couid =c.id AND t.id =tc.teaid AND g.stuid =s.id and s.id="+ID[0]+" and g.classid="+ID[1];
        ResultSet rs=this.executeQuery(sql);
        try {
            if(rs.next()){
                courseEntity= new GradeEntity(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return courseEntity;
    }

    public int update(GradeEntity gradeEntity,String id){
        String sql="update t_grade set grade=? where id=?";
        return this.executeUpdate(sql,gradeEntity.getGrade(),gradeEntity.getId());
    }

    public int insert(GradeEntity gradeEntity){
        String sql="insert into t_grade(classid,stuid) values(?,?)";
        return this.executeUpdate(sql,gradeEntity.getClassId(),gradeEntity.getStuId());
    }

    public void delete(String stuId,String classId){
        String sql="delete from t_grade where stuid="+stuId+" and classid="+classId;
        this.execute(sql);
    }
}
