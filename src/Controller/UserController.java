package Controller;

import entity.UserEntity;
import service.UserService;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ace on 2017/5/24.
 */
public class UserController extends BaseServlet{
    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list",new UserService().view(new UserEntity()));
        req.getRequestDispatcher("/view/user.jsp").forward(req, resp);
    }
}
