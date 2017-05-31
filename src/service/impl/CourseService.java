package service.impl;

import dao.CourseDao;
import entity.CourseEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/5/26.
 */
public class CourseService implements Service{
    private CourseDao courseDao=new CourseDao();
    private CourseEntity search;

    public CourseService() {
    }

    public CourseService(CourseEntity search) {
        this.search = search;
    }

    public int insert(CourseEntity courseEntity){
        return courseDao.insert(courseEntity);
    }

    public int update(CourseEntity courseEntity,String id){
        return courseDao.update(courseEntity,id);
    }

    public CourseEntity getById(String id){
        return courseDao.getByID(id);
    }

    public void delete(String[] id){
        courseDao.deleteByID(id,"t_course");
    }

    @Override
    public List<CourseEntity> getList(){
        return courseDao.getList(search);
    }

}
