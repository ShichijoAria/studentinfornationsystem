package Controller;

import entity.TeacherEntity;
import service.impl.TeacherService;
import util.BaseServlet;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/5/31.
 */
public class TeacherController extends BaseServlet{

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        String id=req.getParameter("searchId");
        String name=req.getParameter("searchName");
        String gender=req.getParameter("searchGender");
        TeacherService teacherService=new TeacherService(new TeacherEntity(id,name,gender));
        List<TeacherEntity> list=teacherService.getList();
        Page page=new Page();
        initialize(page,list.size(),teacherService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchId",id);
        req.setAttribute("searchName",name);
        req.setAttribute("searchGender",gender);
        req.getRequestDispatcher("/view/teacher.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TeacherService teacherService=new TeacherService();
        String id=req.getParameter("resourceId");
        int result=teacherService.update(this.getTeacher(req,resp),id);
        if(result>0) {
            resp.sendRedirect("/SIS/teacher/field?resourceId="+req.getParameter("id"));
        }
        else {
            resp.sendError(404);
        }
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherService teacherService=new TeacherService();
        TeacherEntity teacherEntity=this.getTeacher(req,resp);
        int result=0;
        if(teacherEntity.getId()!=null&&teacherEntity.getId().trim().length()>0)
            result=teacherService.insert(teacherEntity);
        if(result>0) {
            this.setTeacher(req,resp,teacherEntity);
            req.getRequestDispatcher("/teacher/field?id="+teacherEntity.getId()).forward(req, resp);
        }
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherService teacherServices=new TeacherService();
        String id=req.getParameter("resourceId");
        if(id!=null) {
            TeacherEntity courseEntity = teacherServices.getById(id);
            this.setTeacher(req,resp,courseEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("teacherBack",req.getParameter("curPage"));
            req.getRequestDispatcher("/field/teacher.jsp").forward(req, resp);
        }
    }

    private void open(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute("teacherBack",req.getParameter("curPage"));
        req.getRequestDispatcher("/field/teacher.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherService teacherService=new TeacherService();
        String[] items={};
        if(req.getParameterValues("item")!=null) {
            items = req.getParameterValues("item");
            teacherService.delete(items);
        }
        if(req.getParameter("curPage")!=null)
            resp.sendRedirect("/SIS/teacher/view?curPage="+req.getParameter("curPage"));
    }

    public TeacherEntity getTeacher(HttpServletRequest req, HttpServletResponse resp){
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String gender=req.getParameter("gender");
        return new TeacherEntity(id,name,gender);
    }

    public void setTeacher(HttpServletRequest req, HttpServletResponse resp,TeacherEntity teacherEntity){
        req.setAttribute("id",teacherEntity.getId());
        req.setAttribute("name",teacherEntity.getName());
        req.setAttribute("gender",teacherEntity.getGender());
    }

}
