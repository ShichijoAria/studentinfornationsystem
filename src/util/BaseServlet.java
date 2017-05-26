package util;

import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.List;

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
        method.setAccessible(true);
        try {
            Void result = (Void) method.invoke(this, req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str){
        if(str.matches("\\d*"))
            return true;
        else
            return false;
    }

    public void initialize(Page page, int size, Service service, String currentPage){
        int curPage=1;
        if(currentPage!=null&&isNumeric(currentPage))
            curPage=Integer.parseInt(currentPage)<=0?1:Integer.parseInt(currentPage);
        int pages;
        if(size%20==0)
            pages=size/20;
        else
            pages=size/20+1;
        if(curPage>pages)
            curPage=pages;
        this.page(pages,curPage,page,size);
    }

    public void page(int pages,int curPage,Page page,int size){
        if(pages==0) {
            page.setBegin(0);
            page.setEnd(0);
            page.setPages(1);
        }else {
            page.setBegin((curPage - 1) * 20);
            page.setEnd(((curPage - 1) * 20 + 19) < (size - 1) ? ((curPage - 1) * 20 + 19) : (size - 1));
            page.setPages(pages);
        }
    }
}
