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

    @Override
    public List<CourseEntity> getList(){
        return courseDao.getList(search);
    }

}
