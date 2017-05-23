package Controller;

import entity.UserEntity;
import service.UserService;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserController extends BaseServlet{

    private void hello(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp){
        UserEntity user=new UserService().login(new UserEntity(req.getParameter("type"),req.getParameter("id"),req.getParameter("password")));
        if(user!=null) {
            req.getSession().setAttribute("userId",user.getId());
            req.getSession().setAttribute("userPassword",user.getPassword());
            req.getSession().setAttribute("userName",user.getName());
            req.getSession().setAttribute("userType",user.getType());

            System.out.println(req.getSession().getAttribute("userType")+"123");
            try {
                resp.sendRedirect("/SIS/desktop/hello");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.sendRedirect("/SIS/desktop/signIn");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
