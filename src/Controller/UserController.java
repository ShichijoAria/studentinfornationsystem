package Controller;

import entity.UserEntity;
import service.impl.UserService;
import util.BaseServlet;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ace on 2017/5/24.
 */
public class UserController extends BaseServlet{

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curPage=req.getParameter("curPage");
        UserService userService=new UserService(new UserEntity());
        List<UserEntity> list=userService.getList();
        Page page=new Page();
        initialize(page,list.size(),userService,curPage);
        req.setAttribute("page",page);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/view/user.jsp").forward(req, resp);
    }

    private void field(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        String id=this.getId(req,resp);
        if(id!=null) {
            UserEntity userEntity = userService.getById(id);
            this.setUser(req,resp,userEntity);
            if(req.getParameter("curPage")!=null)
                req.getSession().setAttribute("userBack",req.getParameter("curPage"));
            req.getRequestDispatcher("/field/user.jsp").forward(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        String id=getId(req,resp);
        int result=userService.update(this.getUser(req,resp),id);
        if(result>0) {
            req.setAttribute("id",id);
            req.getRequestDispatcher("/user/field?id="+id).forward(req, resp);
        }
        else {
            resp.sendError(404);
        }
    }

    public UserEntity getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String type=req.getParameter("type");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        return new UserEntity(id,type,name,password);
    }

    public void setUser(HttpServletRequest req, HttpServletResponse resp,UserEntity userEntity){
        req.setAttribute("id",userEntity.getId());
        req.setAttribute("name",userEntity.getName());
        req.setAttribute("type",userEntity.getType());
        req.setAttribute("password",userEntity.getPassword());
    }

}
