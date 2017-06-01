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

    public TeacherService(TeacherEntity search) {
        this.search = search;
    }

    public List<TeacherEntity> getList(){
        return teacherDao.getList(search);
    }
}
