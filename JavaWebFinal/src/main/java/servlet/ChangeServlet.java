package servlet;


import dao.ListDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 修改查询页面
 * @author cui
 *
 */
@WebServlet("/changeServlet")
public class ChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        System.out.println(title);
        int titleid = ListDao.findTitleId(title);
        List<String> optionList = ListDao.findTitleOption(titleid);
        List<String> yList = new ArrayList<>();
        for (int i = 2; i < optionList.size(); i++) {
            yList.add(optionList.get(i));
        }
        request.setAttribute("title", title);
        request.setAttribute("list", optionList);
        request.setAttribute("ylist", yList);
        request.getRequestDispatcher("jsp/change.jsp").forward(request, response);
    }

}