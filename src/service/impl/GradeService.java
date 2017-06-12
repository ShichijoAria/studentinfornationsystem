package service.impl;

import dao.GradeDao;
import entity.GradeEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/6/6.
 */
public class GradeService implements Service{
    private GradeDao gradeDao=new GradeDao();
    private GradeEntity search;

    public GradeService() {
    }

    public GradeService(GradeEntity search) {
        this.search = search;
    }

    public int insert(GradeEntity gradeEntity){
        return gradeDao.insert(gradeEntity);
    }

    public int update(float grade,String id){
        return gradeDao.update(grade,id);
    }

    public GradeEntity getById(String id){
        return gradeDao.getByID(id);
    }

    public void delete(String stuId,String classid){
        gradeDao.delete(stuId,classid);
    }

    public List<GradeEntity> getList(long id,int point){
        String sql="SELECT CONCAT(g.stuid,':',g.classid)as id ,t.name,c.name,s.name " +
                "FROM t_student s, t_grade g,t_teacher t,t_teachingclass tc,t_course c " +
                "WHERE g.classid=tc.id AND tc.couid=c.id AND tc.teaid=t.id AND g.stuid=s.id and";
        if(point==3){
            sql+=" s.id="+id;
        }else if(point==2){
            System.out.println(sql);
            sql+=" t.id="+id;
        }
        return gradeDao.getList(sql,search);
    }

    @Override
    public List getList() {
        String sql="SELECT CONCAT(g.stuid,':',g.classid)as id ,t.name,c.name,s.name " +
                "FROM t_student s, t_grade g,t_teacher t,t_teachingclass tc,t_course c " +
                "WHERE g.classid=tc.id AND tc.couid=c.id AND tc.teaid=t.id AND g.stuid=s.id ";
        return gradeDao.getList(sql,search);
    }
}
