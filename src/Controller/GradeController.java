package Controller;

import entity.GradeEntity;
import service.impl.GradeService;
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
 * Created by Ace on 2017/6/6.
 */
public class GradeController extends BaseServlet{

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        List<GradeEntity> list=new ArrayList<GradeEntity>();
        String teaName=req.getParameter("searchTeaName");
        String stuName=req.getParameter("searchStuName");
        String couName=req.getParameter("searchCouName");
        GradeService gradeService=new GradeService(new GradeEntity(teaName,couName,stuName));
        String userType=(String)req.getSession().getAttribute("userType");
        long userId=(long)req.getSession().getAttribute("userId");
        if(userType.equals("1")) {
            list.addAll(gradeService.getList());
        }else if(userType.equals("2")) {
            list.addAll(gradeService.getList(userId,2));
            req.setAttribute("operation", false);
        }else if(userType.equals("3")) {
            list.addAll(gradeService.getList(userId,3));
            req.setAttribute("operation", false);
        }
        Page page=new Page();
        initialize(page,list.size(),gradeService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchTeaName",teaName);
        req.setAttribute("searchStuName",stuName);
        req.setAttribute("searchCouName",couName);
        req.getRequestDispatcher("/view/grade.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        GradeService teachingClassService=new GradeService();
        GradeEntity teachingClassEntity=this.getGrade(req,resp);
        int result=teachingClassService.insert(teachingClassEntity);
        resp.sendRedirect("/SIS/teachingClass/view?curPage="+req.getParameter("curPage"));
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeService gradeService=new GradeService();
        String[] id=req.getParameter("id").split(":");
        gradeService.delete(id[0],id[1]);
        resp.sendRedirect("/SIS/grade/view?curPage="+req.getParameter("curPage"));
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeService gradeService=new GradeService();
        String id=req.getParameter("resourceId");
        req.setAttribute("operation",false);
        if(id!=null) {
            GradeEntity gradeEntity = gradeService.getById(id);
            this.setGrade(req,resp,gradeEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("gradeBack",req.getParameter("curPage"));
            if(((String)req.getSession().getAttribute("userType")).equals("2"))
                req.setAttribute("operation",true);
            req.getRequestDispatcher("/field/grade.jsp").forward(req, resp);
        }else
            resp.sendError(404);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeService gradeService=new GradeService();
        String id=req.getParameter("resourceId");
        float grade=Float.parseFloat(req.getParameter("grade"));
        int result=gradeService.update(grade,id);
        if(result>0) {
            resp.sendRedirect("/SIS/grade/field?resourceId="+id);
        }
        else {
            resp.sendError(404);
        }
    }

    public GradeEntity getGrade(HttpServletRequest req, HttpServletResponse resp){
        long stuId= (long)req.getSession().getAttribute("userId");
        long classId= new MyUtil().getLong(req.getParameter("classId"));
        return new GradeEntity(stuId,classId);
    }

    public void setGrade(HttpServletRequest req, HttpServletResponse resp,GradeEntity gradeEntity){
        req.setAttribute("id",gradeEntity.getId());
        req.setAttribute("stuName",gradeEntity.getStuName());
        req.setAttribute("teaName",gradeEntity.getTeaName());
        req.setAttribute("couName",gradeEntity.getCouName());
        req.setAttribute("grade",gradeEntity.getGrade());
    }
}
