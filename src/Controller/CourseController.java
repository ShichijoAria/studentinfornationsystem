package Controller;

import entity.CourseEntity;
import service.impl.CourseService;
import util.BaseServlet;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ace on 2017/5/26.
 */
public class CourseController extends BaseServlet{
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        CourseService courseService=new CourseService(new CourseEntity());
        List<CourseEntity> list=courseService.getList();
        Page page=new Page();
        initialize(page,list.size(),courseService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/view/course.jsp").forward(req, resp);
    }
}
