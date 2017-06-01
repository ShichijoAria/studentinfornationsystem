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

}
