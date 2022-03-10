package servlet;
import dao.ListDao;
import domain.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 查看投票信息。
 *
 * @author Administrator
 *
 */
@WebServlet("/lookVoteServlet")
public class LookVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取投票标题，选项人数，投票人数
        String title = request.getParameter("title");
        String optionNum = request.getParameter("optionNum");
        String voteNum = request.getParameter("voteNum");

        List<Option> options = new ArrayList<Option>();
        // 根据投票标题ID，查找对应的选项列表。
        int id = ListDao.findTitleId(title);
        List<String> optionList = ListDao.findTitleOption(id);
        for (String string : optionList) {
            Option op = new Option();
            op.setOption(string);
            // 获取该选项的票数
            int num = ListDao.optionNum(string);
            op.setNum(num);
            options.add(op);
        }

        // 获取该投票的所有票数
        int titleNum = ListDao.titleCount(id);

        request.setAttribute("title", title);
        request.setAttribute("optionNum", optionNum);
        request.setAttribute("voteNum", voteNum);
        request.setAttribute("titleNum", titleNum);
        request.setAttribute("options", options);
        request.getRequestDispatcher("jsp/cktp.jsp").forward(request, response);

    }

}
