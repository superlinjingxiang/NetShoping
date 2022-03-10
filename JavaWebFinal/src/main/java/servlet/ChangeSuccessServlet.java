package servlet;


import dao.ListDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 修改投票
 * @author cui
 *
 */
@WebServlet("/changeSuccessServlet")
public class ChangeSuccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");

        int titleid = ListDao.findTitleId(title);
        // 删除投票表的信息
        ListDao.delVote(titleid);
        // 删除选项表的信息
        ListDao.delOption(titleid);
        // 删除标题表的信息。
        ListDao.delArticle(titleid);

        String type = request.getParameter("type");
        int tp;
        if (type.equals("dan")) {
            tp = 0;
        } else {
            tp = 1;
        }

        // 添加投票表的信息。
        int id = ListDao.addTitle(title, tp);

        String[] option = request.getParameterValues("option");
        // 添加选项表的信息。
        for (String string : option) {
            ListDao.addOption(string, id);
        }
        response.getWriter()
                .print("<script language='JavaScript'>alert('修改成功');window.location.href='jsp/tpList.jsp';</script>");
    }
}
