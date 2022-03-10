package servlet;
import dao.ListDao;
import dao.UserDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/whetherVoteServlet")
public class WhetherVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WhetherVoteServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String optionNum = request.getParameter("optionNum");
        String voteNum = request.getParameter("voteNum");

        int titleid = ListDao.findTitleId(title);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int userid = UserDao.findUserId(username);


        List<String> slist = ListDao.findTitleOption(titleid);


        List<Integer> inte = ListDao.findUserOption(titleid, userid);
        List<String> sinte = new ArrayList<>();
        for (int i : inte) {
            sinte.add(ListDao.findOption(i));
        }

        for (String string : sinte) {
            System.out.println(string);
        }
//对象保存在request作用域中，然后在转发进入的页面就可以获取到你的值
        request.setAttribute("title", title);
        request.setAttribute("optionNum", optionNum);
        request.setAttribute("voteNum", voteNum);
        request.setAttribute("list", slist);
        request.setAttribute("sinte", sinte);
//        已经投票的页面展示
//        RequestDispatcher对象从客户端获取请求request，并把它们传递给服务器上的servlet,html或jsp。
        request.getRequestDispatcher("jsp/yesVote.jsp").forward(request, response);

    }

}
