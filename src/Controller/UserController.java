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

    private void login(HttpServletRequest req, HttpServletResponse resp){
        UserEntity user=new UserService().login(new UserEntity(req.getParameter("type"),req.getParameter("id"),req.getParameter("password")));
        System.out.println(user+"5");
        if(user!=null) {
            try {
                System.out.println(user.getId()+user.getPassword());
                HttpSession session=req.getSession();
                session.setAttribute("userId",user.getId());
                session.setAttribute("userPassword",user.getPassword());
                session.setAttribute("userName",user.getName());
                session.setAttribute("userType",user.getType());
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
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
