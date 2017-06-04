package interceptor;

import entity.UserEntity;
import service.impl.UserService;
import util.MyUtil;

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
        String id=String.valueOf(session.getAttribute("userId"));
        UserEntity u=new UserService().login(new UserEntity(new MyUtil().getLong(id),(String)session.getAttribute("userType"),(String)session.getAttribute("userPassword")));
            if(u!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            resp.sendRedirect("/SIS/desktop/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
