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

    public int insert(TeachingClassEntity teachingClassEntity){
        return teachingClassDao.insert(teachingClassEntity);
    }

    public TeachingClassService(TeachingClassEntity search) {
        this.search = search;
    }

    @Override
    public List getList() {
        String s="";
        if(search!=null) {
            s += " and c.name like '%";
            s +=search.getCouName()==null?"":search.getCouName();
            s +="%' and t.name like '%";
            s +=search.getTeaName()==null?"":search.getTeaName();
            s +="%'";
        }
        return teachingClassDao.getList("SELECT tc.*,c.name,t.name FROM t_teachingclass tc,t_teacher t,t_course c WHERE " +
                    "tc.couid=c.id AND tc.teaid =t.id "+s);
    }

    public TeachingClassEntity getById(String id){
        return teachingClassDao.getByID(id);
    }

}
