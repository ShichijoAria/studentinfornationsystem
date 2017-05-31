package Controller;

import entity.TeacherEntity;
import service.impl.TeacherService;
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
public class TeacherController {

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     /*   String curPage=req.getParameter("curPage");
        TeacherService courseService=new TeacherService(new TeacherEntity((String)req.getParameter("searchId"),
                (String)req.getParameter("searchName")));
        List<CourseEntity> list=new ArrayList<CourseEntity>();
        list.addAll(courseService.getList());
        Page page=new Page();
        initialize(page,list.size(),courseService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.setAttribute("searchId",(String)req.getAttribute("searchId"));
        req.setAttribute("searchName",(String)req.getAttribute("searchName"));
        req.getRequestDispatcher("/view/course.jsp").forward(req, resp);*/
    }

}
