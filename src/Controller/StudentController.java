package Controller;

import entity.StudentEntity;
import service.impl.StudentService;
import util.BaseServlet;
import util.MyUtil;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ace on 2017/6/1.
 */
public class StudentController extends BaseServlet{

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        long id=new MyUtil().getLong(req.getParameter("searchId"));
        String name=req.getParameter("searchName");
        String gender=req.getParameter("searchGender");
        StudentService studentService=new StudentService(new StudentEntity(id,name,gender));
        List<StudentEntity> list=studentService.getList();
        Page page=new Page();
        initialize(page,list.size(),studentService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchId",id);
        req.setAttribute("searchName",name);
        req.setAttribute("searchGender",gender);
        req.getRequestDispatcher("/view/student.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StudentService teacherService=new StudentService();
        String id=req.getParameter("resourceId");
        int result=teacherService.update(this.getStudent(req,resp),id);
        if(result>0) {
            resp.sendRedirect("/SIS/student/field?resourceId="+req.getParameter("id"));
        }
        else {
            resp.sendError(404);
        }
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService=new StudentService();
        StudentEntity studentEntity=this.getStudent(req,resp);
        int result=0;
        if(req.getParameter("id")!=null&&req.getParameter("id").trim().length()>0)
            result=studentService.insert(studentEntity);
        if(result>0) {
            resp.sendRedirect("/SIS/student/field?resourceId="+studentEntity.getId());
        }
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService=new StudentService();
        String id=req.getParameter("resourceId");
        if(id!=null) {
            StudentEntity courseEntity = studentService.getById(id);
            this.setStudent(req,resp,courseEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("studentBack",req.getParameter("curPage"));
            req.getRequestDispatcher("/field/student.jsp").forward(req, resp);
        }
    }

    private void open(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().setAttribute("studentBack",req.getParameter("curPage"));
        req.getRequestDispatcher("/field/student.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService=new StudentService();
        String[] items={};
        if(req.getParameterValues("item")!=null) {
            items = req.getParameterValues("item");
            studentService.delete(items);
        }
        if(req.getParameter("curPage")!=null)
            resp.sendRedirect("/SIS/student/view?curPage="+req.getParameter("curPage"));
    }

    public StudentEntity getStudent(HttpServletRequest req, HttpServletResponse resp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long id=Long.parseLong(req.getParameter("id"));
        String name=req.getParameter("name");
        String gender=req.getParameter("gender");
        String faculty=req.getParameter("faculty");
        String grade=req.getParameter("grade");
        String itemClass=req.getParameter("itemClass");
        Date timeOfEn= null;
        try {
            timeOfEn = sdf.parse(req.getParameter("timeOfEn"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String identity=req.getParameter("identity");
        return new StudentEntity(id,name,gender,faculty,grade,itemClass,timeOfEn,identity);
    }

    public void setStudent(HttpServletRequest req, HttpServletResponse resp,StudentEntity studentEntity){
        req.setAttribute("id",studentEntity.getId());
        req.setAttribute("name",studentEntity.getName());
        req.setAttribute("gender",studentEntity.getGender());
        req.setAttribute("faculty",studentEntity.getFaculty());
        req.setAttribute("grade",studentEntity.getGrade());
        req.setAttribute("itemClass",studentEntity.getItemClass());
        req.setAttribute("timeOfEn",studentEntity.getTimeOfEn());
        req.setAttribute("identity",studentEntity.getIdentity());
    }

}
