package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * Created by Ace on 2017/5/22.
 */
public class BaseServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName=req.getPathInfo();
        methodName=methodName.substring(1,methodName.length());
        Method method = null;

        // 2、通过方法名和方法所需要的参数获得Method对象
        try {
            method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("调用的方法：" + methodName + "不存在", e);
        }

        // 3、通过Method对象调用方法
        try {
            method.setAccessible(true);
            String result = (String) method.invoke(this, req, resp);
            System.out.println(result);
            if (result != null && result.trim().length() > 0) {// 如果返回的result不为空
                int index = result.indexOf(":");// 获得第一个冒号的位置
                if (index == -1) {// 如果没有冒号，就使用转发
                    req.getRequestDispatcher(result).forward(req, resp);
                } else {// 如果有冒号
                    String start = result.substring(0, index);// 截取前缀
                    String path = result.substring(index + 1);// 截取路径
                    if (start.equalsIgnoreCase("f")) {// 前缀为f表示使用转发
                        req.getRequestDispatcher(path).forward(req, resp);
                    } else if (start.equalsIgnoreCase("r")) {// 前缀为r表示使用重定向
                        resp.sendRedirect(req.getContextPath() + path);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
