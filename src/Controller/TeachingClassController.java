package Controller;

import dao.CourseDao;
import dao.TeacherDao;
import entity.CourseEntity;
import entity.TeacherEntity;
import entity.TeachingClassEntity;
import service.impl.TeachingClassService;
import util.BaseServlet;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/3.
 */
public class TeachingClassController extends BaseServlet{
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        String teaName=req.getParameter("searchTeaName");
        String couName=req.getParameter("searchCouName");
        TeachingClassService teachingClassService=new TeachingClassService(new TeachingClassEntity(couName,teaName));
        List<TeachingClassEntity> list=new ArrayList<TeachingClassEntity>();
        list.addAll(teachingClassService.getList());
        Page page=new Page();
        initialize(page,list.size(),teachingClassService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchTeaName",teaName);
        req.setAttribute("searchCouName",couName);
        req.getRequestDispatcher("/view/teachingClass.jsp").forward(req, resp);
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeachingClassService teachingClassService=new TeachingClassService();
        String id=req.getParameter("resourceId");
        if(id!=null) {
            TeachingClassEntity teachingClassEntity = teachingClassService.getById(id);
            this.setTeachingClass(req,resp,teachingClassEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("courseBack",req.getParameter("curPage"));
            this.setList(req,resp);
            req.setAttribute("operation",true);
            req.getRequestDispatcher("/field/teachingClass.jsp").forward(req, resp);
        }else
            resp.sendError(404);
    }

    private void open(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute("teachingClassBack",req.getParameter("curPage"));
        this.setList(req,resp);
        req.setAttribute("operation",true);
        req.getRequestDispatcher("/field/teachingClass.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TeachingClassService teachingClassService=new TeachingClassService();
        TeachingClassEntity teachingClassEntity=this.getTeachingClass(req,resp);
        int result=0;
        result=teachingClassService.insert(teachingClassEntity);
        if(result>0) {
            resp.sendRedirect("/SIS/course/field?resourceId="+teachingClassEntity.getId());
        }
    }

    public TeachingClassEntity getTeachingClass(HttpServletRequest req, HttpServletResponse resp){
        String id=req.getParameter("couId");
        String name=req.getParameter("teaId");
        return new TeachingClassEntity(id,name);
    }

    public void setTeachingClass(HttpServletRequest req, HttpServletResponse resp,TeachingClassEntity teachingClassEntity){
        req.setAttribute("couId",teachingClassEntity.getCouId());
        req.setAttribute("teaId",teachingClassEntity.getTeaId());
    }

    public void setList(HttpServletRequest req, HttpServletResponse resp){
        List<CourseEntity> couList=new CourseDao().getList(null);
        List<TeacherEntity> teaList=new TeacherDao().getList(null);
        req.setAttribute("couList",couList);
        req.setAttribute("teaList",teaList);
    }
}
