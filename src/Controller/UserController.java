package Controller;

import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserController extends BaseServlet{

    private void login(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("login");
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
