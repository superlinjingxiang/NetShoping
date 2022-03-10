package servlet;

import dao.UserDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 注册
 * @author cui
 *
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int flag = UserDao.register(username, password);
//        flag>0数据库插入数据正常
        if (flag > 0) {
            response.sendRedirect("jsp/regS.jsp");
        } else {
            response.sendRedirect("jsp/reg.jsp");
        }
    }

}
