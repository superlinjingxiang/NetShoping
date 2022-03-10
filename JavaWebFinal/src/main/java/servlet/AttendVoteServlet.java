package servlet;


import dao.ListDao;
import dao.UserDao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 投票前查询信息，添加投票
 * @author cui
 *
 */
@WebServlet("/attendVoteServlet")
public class AttendVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String types = request.getParameter("type");
        if (types == null || types.equals("")) {
            // 获取当前投票数据，并跳转到投票页面
            request.setCharacterEncoding("utf-8");
//            读取网页的数据
            String title = request.getParameter("title");
            String optionNum = request.getParameter("optionNum");
            String voteNum = request.getParameter("voteNum");
            //用这个网页得到的title去查他的id
            int id = ListDao.findTitleId(title);
            //id查它的选项   给他放进optionList集合
            List<String> optionList = ListDao.findTitleOption(id);
            //用id查他的状态   看看有没有投过票
            int type = ListDao.findTitleType(id);
            /**
             * title optionNum voteNum type optionList
             * 再返回给浏览器
             * request.setAttribute在请求域里面加了一个请求的参数
             *再请求转发到下一个页面。
             * ps：这里不是重定向 重定向是没办法得到这个请求参数的
             * sendRedirect是重定向，可以在同一个容器里使用，也可以发送其他容器请求，但是会丢失请求信息。它等于重发一个请求
             */
            request.setAttribute("title", title);
            request.setAttribute("optionNum", optionNum);
            request.setAttribute("voteNum", voteNum);
            request.setAttribute("type", type);
            request.setAttribute("optionList", optionList);
            request.getRequestDispatcher("jsp/cytp.jsp").forward(request, response);
            //交给cytp.jsp这个页面













        } else {
            // 完成投票操作
            request.setCharacterEncoding("utf-8");
            /**
             * 一个session的概念需要包括特定的客户端，
             * 特定的服务器端以及不中断的操作时间。
             * A用户和C服务器建立连接时所处的session同B用户和C服务器建立连接时所处的Session是两个不同的session。
             */
            HttpSession session = request.getSession();
            //得到你当前网页于其客户端的一个session对象  找到他的username
            String name = (String) session.getAttribute("username");
            //name 找id
            int userId = UserDao.findUserId(name);
            //获取网页的这个标题  刚刚请求转发过来的
            String title = request.getParameter("title");
            int titleId = ListDao.findTitleId(title);
            String type = request.getParameter("type");
            if (type.equals("0")) {
                String option = request.getParameter("radio");
                int optionid = ListDao.findOptionId(option);

                ListDao.addvote(titleId, optionid, userId);
            } else {
                String option[] = request.getParameterValues("chbox");
                for (String string : option) {
                    System.out.println(string);
                    int optionid = ListDao.findOptionId(string);
                    ListDao.addvote(titleId, optionid, userId);
                }
            }
            response.getWriter().print(
                    "<script language='JavaScript'>alert('增加信息成功');window.location.href='jsp/tpList.jsp';</script>");
        }

    }

}
