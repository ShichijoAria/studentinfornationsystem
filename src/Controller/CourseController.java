package Controller;

import entity.CourseEntity;
import service.impl.CourseService;
import util.BaseServlet;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/5/26.
 */
public class CourseController extends BaseServlet{

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService courseService=new CourseService();
        CourseEntity courseEntity=this.getCourse(req,resp);
        int result=0;
        if(courseEntity.getId()!=null&&courseEntity.getId().trim().length()>0)
            result=courseService.insert(courseEntity);
        if(result>0) {
            this.setCourse(req,resp,courseEntity);
            req.getRequestDispatcher("/course/field?id="+courseEntity.getId()).forward(req, resp);
        }
    }

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        CourseService courseService=new CourseService(new CourseEntity((String)req.getParameter("searchId"),
                (String)req.getParameter("searchName")));
        List<CourseEntity> list=new ArrayList<CourseEntity>();
        list.addAll(courseService.getList());
        Page page=new Page();
        initialize(page,list.size(),courseService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchId",(String)req.getAttribute("searchId"));
        req.setAttribute("searchName",(String)req.getAttribute("searchName"));
        req.getRequestDispatcher("/view/course.jsp").forward(req, resp);
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService courseService=new CourseService();
        String id=this.getId(req,resp);
        if(id!=null) {
            CourseEntity courseEntity = courseService.getById(id);
            this.setCourse(req,resp,courseEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("courseBack",req.getParameter("curPage"));
            req.getRequestDispatcher("/field/course.jsp").forward(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService=new CourseService();
        String id=this.getId(req,resp);
        int result=courseService.update(this.getCourse(req,resp),id);
        if(result>0) {
            req.setAttribute("id",id);
            req.getRequestDispatcher("/course/field?id="+id).forward(req, resp);
        }
        else {
            resp.sendError(404);
        }
    }

    private void open(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute("courseBack",req.getParameter("curPage"));
        req.getRequestDispatcher("/field/course.jsp").forward(req, resp);
    }

    public CourseEntity getCourse(HttpServletRequest req, HttpServletResponse resp){
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        return new CourseEntity(id,name);
    }

    public void setCourse(HttpServletRequest req, HttpServletResponse resp,CourseEntity courseEntity){
        req.setAttribute("id",courseEntity.getId());
        req.setAttribute("name",courseEntity.getName());
    }

}
