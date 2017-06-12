package Controller;

import entity.CourseEntity;
import service.impl.CourseService;
import util.BaseServlet;
import util.MyUtil;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        if(req.getParameter("id")!=null&&req.getParameter("id").trim().length()>0)
        result=courseService.insert(courseEntity);
        if(result>0) {
            resp.sendRedirect("/SIS/course/field?resourceId="+courseEntity.getId());
        }else {
            resp.sendError(404);
        }
    }

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        long id=new MyUtil().getLong(req.getParameter("searchId"));
        String name=req.getParameter("searchName");
        CourseService courseService=new CourseService(new CourseEntity(id,name));
        List<CourseEntity> list=new ArrayList<CourseEntity>();
        list.addAll(courseService.getList());
        Page page=new Page();
        initialize(page,list.size(),courseService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchId",id);
        req.setAttribute("searchName",name);
        req.getRequestDispatcher("/view/course.jsp").forward(req, resp);
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService=new CourseService();
        String id=req.getParameter("resourceId");
        if(id!=null) {
            CourseEntity courseEntity = courseService.getById(id);
            this.setCourse(req,resp,courseEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("courseBack",req.getParameter("curPage"));
            req.getRequestDispatcher("/field/course.jsp").forward(req, resp);
        }else
            resp.sendError(404);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CourseService courseService=new CourseService();
        String id=req.getParameter("resourceId");
        int result=courseService.update(this.getCourse(req,resp),id);
        if(result>0) {
            resp.sendRedirect("/SIS/course/field?resourceId="+req.getParameter("id"));
        }
        else {
            resp.sendError(404);
        }
    }

    private void open(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute("courseBack",req.getParameter("curPage"));
        req.getRequestDispatcher("/field/course.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseService courseService=new CourseService();
        String[] items={};
        if(req.getParameterValues("item")!=null) {
            items = req.getParameterValues("item");
            courseService.delete(items);
        }
        if(req.getParameter("curPage")!=null)
            resp.sendRedirect("/SIS/course/view?curPage="+req.getParameter("curPage"));
    }

    public CourseEntity getCourse(HttpServletRequest req, HttpServletResponse resp){
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        return new CourseEntity(Long.parseLong(id),name);
    }

    public void setCourse(HttpServletRequest req, HttpServletResponse resp,CourseEntity courseEntity){
        req.setAttribute("id",courseEntity.getId());
        req.setAttribute("name",courseEntity.getName());
    }

}
