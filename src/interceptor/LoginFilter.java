package interceptor;

import entity.UserEntity;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ace on 2017/5/22.
 */
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session=req.getSession();
        if(new UserService().login(new UserEntity((String)session.getAttribute("type"),(String)session.getAttribute("id"),(String)session.getAttribute("password")))!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            resp.sendRedirect("/SIS/login/login.jsp");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
