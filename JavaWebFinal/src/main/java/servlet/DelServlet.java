package servlet;
import dao.ListDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 删除功能
 * @author cui
 *
 */
@WebServlet("/delServlet")
public class DelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        int titleid = ListDao.findTitleId(title);
        // 删除投票表的信息
        ListDao.delVote(titleid);
        // 删除选项表的信息
        ListDao.delOption(titleid);
        // 删除标题表的信息。
        ListDao.delArticle(titleid);
        response.sendRedirect("jsp/tpList.jsp");

    }

}
