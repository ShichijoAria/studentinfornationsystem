package service.impl;

import dao.StudentDao;
import entity.StudentEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/6/1.
 */
public class StudentService implements Service{
    private StudentDao studentDao=new StudentDao();
    private StudentEntity search;

    public StudentService() {
    }

    public StudentService(StudentEntity search) {
        this.studentDao = studentDao;
        this.search = search;
    }

    @Override
    public List getList() {
        return studentDao.getList(search);
    }

    public StudentEntity getById(String id){
        return studentDao.getByID(id);
    }

    public int update(StudentEntity teacherEntity,String id){
        return studentDao.update(teacherEntity,id);
    }

    public int insert(StudentEntity courseEntity){
        return studentDao.insert(courseEntity);
    }

    public void delete(String[] id){
        studentDao.deleteByID(id,"t_student");
    }

}
