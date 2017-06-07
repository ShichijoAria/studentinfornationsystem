package service.impl;

import dao.TeachingClassDao;
import entity.TeachingClassEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/6/3.
 */
public class TeachingClassService implements Service{
    private TeachingClassDao teachingClassDao=new TeachingClassDao();
    private TeachingClassEntity search;

    public TeachingClassService() {
    }

    public TeachingClassEntity insert(TeachingClassEntity teachingClassEntity){
        return teachingClassDao.insert(teachingClassEntity);
    }

    public int update(TeachingClassEntity teachingClassEntity, long id){
        return teachingClassDao.update(teachingClassEntity,id);
    }

    public TeachingClassService(TeachingClassEntity search) {
        this.search = search;
    }

    @Override
    public List getList() {
        return teachingClassDao.getList("SELECT tc.*,c.name,t.name FROM t_teachingclass tc,t_teacher t,t_course c WHERE " +
                    "tc.couid=c.id AND tc.teaid =t.id "+this.condition());
    }

    public TeachingClassEntity getById(long id){
        return teachingClassDao.getByID(id);
    }

    public void delete(String[] id){
        teachingClassDao.deleteById(id,"t_teachingClass");
    }

    public List getList(long id,int point){
        if(point==2)
            return teachingClassDao.getList("SELECT tc.*,c.name,t.name FROM t_teachingclass tc,t_teacher t,t_course c WHERE " +
                "tc.couid=c.id AND tc.teaid =t.id and t.id="+id+" "+this.condition());
        return teachingClassDao.getList("SELECT  tc.*,c.name,t.name " +
                "FROM t_teachingclass tc,t_teacher t,t_course c  " +
                "WHERE tc.couid=c.id AND tc.teaid=t.id " +
                " AND tc.id NOT IN" +
                " (SELECT tc.id " +
                " FROM t_teachingclass tc,t_course c,t_grade g,t_student s WHERE s.id="+id+
                " AND c.id=tc.couid AND g.classid=tc.id AND s.id=g.stuid) "+this.condition());
    }

    public String condition(){
        String s="";
        if(search!=null) {
            s += " and c.name like '%";
            s +=search.getCouName()==null?"":search.getCouName();
            s +="%' and t.name like '%";
            s +=search.getTeaName()==null?"":search.getTeaName();
            s +="%'";
        }
        return s;
    }
}
