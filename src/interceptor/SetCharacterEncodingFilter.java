package interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetCharacterEncodingFilter implements Filter { //要实现Filter接口

  @Override
  public void destroy()
  {
   // TODO Auto-generated method stub

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
  {
    request.setCharacterEncoding("utf-8");
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException
  {
   // TODO Auto-generated method stub
  }
   
}  