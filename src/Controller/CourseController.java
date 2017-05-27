package Controller;

import entity.CourseEntity;
import service.impl.CourseService;
import util.BaseServlet;
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

    private void field(HttpServletRequest req, HttpServletResponse resp){
        CourseService courseService=new CourseService();
        String id=this.getId(req,resp);
        if(id!=null) {
            CourseEntity courseEntity = courseService.getById(id);
            req.setAttribute("id",courseEntity.getId());
            req.setAttribute("name",courseEntity.getName());
        }
    }

}
