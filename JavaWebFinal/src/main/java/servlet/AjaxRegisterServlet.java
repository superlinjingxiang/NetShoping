package servlet;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断用户是否已注册
 * @author Administrator
 *
 */

@WebServlet("/AjaxRegisterServlet")
public class AjaxRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name= request.getParameter("user_name");
        response.setCharacterEncoding("UTF-8");
        boolean flag= UserDao.idRegister(name);
        if(flag){
            System.out.println("用户名已被注册");
            response.getWriter().print("用户名已被注册");
        }else {
            response.getWriter().print("用户名可以使用");
        }

    }

}
