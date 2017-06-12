package dao;

import entity.CountGradeEntity;
import entity.CourseEntity;
import entity.GradeEntity;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
        String sql="SELECT CONCAT(s.id,':',tc.id),t.name,c.name,s.name,g.grade \n" +
                "FROM t_grade g,t_student s,t_teacher t,t_course c,t_teachingclass tc \n" +
                "WHERE g.classid=tc.id AND tc.couid =c.id AND t.id =tc.teaid AND g.stuid =s.id and s.id="+ID[0]+" and g.classid="+ID[1];
        ResultSet rs=this.executeQuery(sql);
        try {
            if(rs.next()){
                courseEntity= new GradeEntity(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getFloat(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return courseEntity;
    }

    public int update(float grade,String id){
        String ID[]=id.split(":");
        String sql="update t_grade set grade=? where stuid=? and classid=?";
        return this.executeUpdate(sql,grade,ID[0],ID[1]);
    }

    public int insert(GradeEntity gradeEntity){
        String sql="insert into t_grade(classid,stuid) values(?,?)";
        return this.executeUpdate(sql,gradeEntity.getClassId(),gradeEntity.getStuId());
    }

    public void delete(String stuId,String classId){
        String sql="delete from t_grade where stuid="+stuId+" and classid="+classId;
        this.execute(sql);
    }

    public List<CountGradeEntity> getGrade(long teaId, long couId){
        String sql="SELECT g.grade,s.name FROM t_grade g,t_teachingclass tc ,t_course c,t_student s " +
                "WHERE g.stuid= s.id AND c.id=tc.couid AND tc.id=g.classid and tc.teaid="+teaId+" and c.id="+couId+" ORDER BY g.grade";
        List<CountGradeEntity> list = new ArrayList<CountGradeEntity>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new CountGradeEntity(rs.getFloat(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return list;
    }

    public long countCourses(long id){
        String sql="SELECT count(*) FROM t_grade " +
                " WHERE stuid="+id;
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return 0;
    }

    public List<GradeEntity> getGrades(long id){
        String sql="SELECT g.grade,c.name FROM t_grade g,t_teachingclass tc,t_course c" +
                " WHERE g.classid= tc.id AND tc.couid =c.id and g.stuid="+id;
        List<GradeEntity> list = new ArrayList<GradeEntity>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new GradeEntity(rs.getString(2),rs.getFloat(1)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return list;
    }

    public HashMap<CourseEntity,List<CountGradeEntity>> countGrades(long id){
        String sql="SELECT c.id,c.name FROM t_course c,t_teachingclass tc" +
                " WHERE tc.couid=c.id and tc.teaid="+id+" GROUP BY tc.id";
        HashMap<CourseEntity,List<CountGradeEntity>> map = new HashMap<CourseEntity,List<CountGradeEntity>>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                CourseEntity courseEntity=new CourseEntity(rs.getLong(1),rs.getString(2));
                map.put(courseEntity,this.getGrade(id,courseEntity.getId()));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.close();
        }
        return map;
    }
}
