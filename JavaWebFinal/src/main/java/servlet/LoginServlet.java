package servlet;
import dao.UserDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author cui
 *
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * username
         * password     从网页上获取这两个值
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //     用username password这两个值去查   是否查得到的值用flag 返回   此处用的不是比对
        boolean flag = UserDao.login(username, password);
        if (flag) {
            //flag = true 查得到
            HttpSession session = request.getSession();
            session.setAttribute("username", username);//String username = request.getParameter("username")
            //重定向index.jsp
            response.sendRedirect("jsp/index.jsp");
        } else {
//            网页上的值msg 跳用户密码错误
            request.setAttribute("msg", "用户密码错误");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

    }

}
