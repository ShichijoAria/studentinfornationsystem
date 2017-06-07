package service.impl;

import dao.TeacherDao;
import entity.TeacherEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/5/31.
 */
public class TeacherService implements Service{
    private TeacherDao teacherDao=new TeacherDao();
    private TeacherEntity search;

    public TeacherService() {
    }

    public TeacherEntity getById(String id){
        return teacherDao.getByID(id);
    }

    public int update(TeacherEntity teacherEntity,String id){
        return teacherDao.update(teacherEntity,id);
    }

    public int insert(TeacherEntity courseEntity){
        return teacherDao.insert(courseEntity);
    }

    public TeacherService(TeacherEntity search) {
        this.search = search;
    }

    public void delete(String[] id){
        teacherDao.deleteById(id,"t_teacher");
    }

    public List<TeacherEntity> getList(){
        return teacherDao.getList(search);
    }
}
