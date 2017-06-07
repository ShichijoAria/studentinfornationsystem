package Controller;

import dao.CourseDao;
import dao.TeacherDao;
import entity.CourseEntity;
import entity.TeacherEntity;
import entity.TeachingClassEntity;
import service.impl.TeachingClassService;
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
 * Created by Ace on 2017/6/3.
 */
public class TeachingClassController extends BaseServlet{
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        String teaName=req.getParameter("searchTeaName");
        String couName=req.getParameter("searchCouName");
        long userId=(long)req.getSession().getAttribute("userId");
        String userType=(String)req.getSession().getAttribute("userType");
        TeachingClassService teachingClassService=new TeachingClassService(new TeachingClassEntity(couName,teaName));
        List<TeachingClassEntity> list=new ArrayList<TeachingClassEntity>();
        if(userType.equals("1")) {
            list.addAll(teachingClassService.getList());
            req.setAttribute("operation",true);
        }else if(userType.equals("2")) {
            list.addAll(teachingClassService.getList(userId,2));
            req.setAttribute("operation", false);
        }else if(userType.equals("3")) {
            list.addAll(teachingClassService.getList(userId,3));
            req.setAttribute("operation", false);
        }
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
        req.setAttribute("operation",false);
        long id=new MyUtil().getLong(req.getParameter("resourceId"));
        if(id>=0) {
            TeachingClassEntity teachingClassEntity = teachingClassService.getById(id);
            this.setTeachingClass(req,resp,teachingClassEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("teachingClassBack",req.getParameter("curPage"));
            this.setList(req,resp);
            if(((String)req.getSession().getAttribute("userType")).equals("1"))
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
        TeachingClassEntity newTeachingClassEntity=teachingClassService.insert(teachingClassEntity);
        resp.sendRedirect("/SIS/teachingClass/field?resourceId="+newTeachingClassEntity.getId());
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TeachingClassService teachingClassService=new TeachingClassService();
        String id=req.getParameter("resourceId");
        int result=teachingClassService.update(this.getTeachingClass(req,resp),Long.valueOf(id));
        if(result>0) {
            resp.sendRedirect("/SIS/teachingClass/field?resourceId="+id);
        }
        else {
            resp.sendError(404);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeachingClassService teachingClassService=new TeachingClassService();
        String[] items={};
        if(req.getParameterValues("item")!=null) {
            items = req.getParameterValues("item");
            teachingClassService.delete(items);
        }
        if(req.getParameter("curPage")!=null)
            resp.sendRedirect("/SIS/teachingClass/view?curPage="+req.getParameter("curPage"));
    }

    public TeachingClassEntity getTeachingClass(HttpServletRequest req, HttpServletResponse resp){
        long couId= new MyUtil().getLong(req.getParameter("couId"));
        long teaId= new MyUtil().getLong(req.getParameter("teaId"));
        return new TeachingClassEntity(couId,teaId);
    }

    public void setTeachingClass(HttpServletRequest req, HttpServletResponse resp,TeachingClassEntity teachingClassEntity){
        req.setAttribute("couId",teachingClassEntity.getCouId());
        req.setAttribute("teaId",teachingClassEntity.getTeaId());
        req.setAttribute("id",teachingClassEntity.getId());
    }

    public void setList(HttpServletRequest req, HttpServletResponse resp){
        List<CourseEntity> couList=new CourseDao().getList(null);
        List<TeacherEntity> teaList=new TeacherDao().getList(null);
        req.setAttribute("couList",couList);
        req.setAttribute("teaList",teaList);
    }
}
