package servlet;

import dao.ListDao;
import dao.UserDao;
import domain.Listing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/articleListServlet")
public class ArticleListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");//解决网页表单获取到的是乱码的问题
        String flag = request.getParameter("flag");//创建一个字符串flag 用于获取功能

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 用于判断是否是维护状态
        String hi = request.getParameter("hi");
        System.out.println(hi);
        if (hi != null && hi.equals("1")) {
            // 如果hi等于1，列表则显示为维护状态
            request.setAttribute("del", "d");
        }

        int userid = UserDao.findUserId(username);
        String del = request.getParameter("del");
        if (del != null && del.equals("d")) {
            request.setAttribute("del", del);
        }

        // 页数
        // 第一次访问，首页是1
        int goPage = 1;
        // 获取投票列表记录数
        int count = ListDao.artlcleCount();
        // 计算总页数
        int pageCount;
        // 每页显示5条
        if (count % 5 != 0) {
            pageCount = count / 5 + 1;
        } else {
            pageCount = count / 5;
        }

        // 获取前端页面当前页数
        String pagestr = request.getParameter("goPage");
        if (pagestr != null && !pagestr.equals("")) {
            goPage = Integer.parseInt(pagestr);
        }

        // 页数边界限制
        if (goPage <= 0) {
            goPage = 1;
        }
        if (goPage > pageCount) {
            goPage = pageCount;
        }
        request.setAttribute("goPage", goPage);
//      如果网页没有传flag的值，没有改变flag的值   预示着没有做操作  正常显示
        if (flag == null || flag.equals("")) {
//            创建一个List集合存储Listing对象   此对象为数据库查到的一个集合
            List<Listing> list = new ArrayList<>();
            list = ListDao.viewList(null, (goPage - 1) * 5, 5);
            //开始遍历集合
            /**
             * 每一遍就把一条数据的标题id 拉出来 titleid
             * 用来出来的titleid  userid  给userIfVote方法再去数据库用这两个查询
             * 如果查到了 isvote改变状态付给这个listing集合
             * 定义是否投票
             */
            for (Listing listing : list) {
                int titleid = ListDao.findTitleId(listing.getTitle());
                boolean isvote = UserDao.userIfVote(titleid, userid);
                listing.setIsVote(isvote);
            }
//            把这个集合交给网页
            request.setAttribute("list", list);
            //请求转发  tpList页面进行显示
            request.getRequestDispatcher("jsp/tpList.jsp").forward(request, response);
        } else if (flag.equals("search")) {//搜索表单提交之后会把flag的值改变为search
            // 搜索框模糊查询
            String title = request.getParameter("search");
            List<Listing> list = new ArrayList<>();
            list = ListDao.viewList(title, (goPage - 1) * 5, 5);
            /**
             * 每一遍就把一条数据的标题id 拉出来 titleid
             * 用来出来的titleid  userid  给userIfVote方法再去数据库用这两个查询
             * 如果查到了 isvote改变状态付给这个listing集合
             * 定义是否投票
             */
            for (Listing listing : list) {
                int titleid = ListDao.findTitleId(listing.getTitle());
                boolean isvote = UserDao.userIfVote(titleid, userid);
                listing.setIsVote(isvote);
            }
            // 模糊搜索的数据集合
            if (list.size() == 0) {
                // 如果没搜索到。
                request.setAttribute("flag", 1);
                request.getRequestDispatcher("jsp/tpList.jsp").forward(request, response);
            } else {
                request.setAttribute("list", list);
                request.getRequestDispatcher("jsp/tpList.jsp").forward(request, response);
            }
        } else if (flag.equals("add")) {//网页修改flag的值
            // 新增投票。
            request.setCharacterEncoding("utf-8");
            //解决网页表单获取到的是乱码的问题

            String title = request.getParameter("title");
            boolean is = ListDao.isReleaseVote(title);
            if (is) {
                response.getWriter().print(
                        "<script language='JavaScript'>alert('添加失败');window.location.href='jsp/tpList.jsp';</script>");
            } else {
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
                response.getWriter().print(
                        "<script language='JavaScript'>alert('添加成功');window.location.href='jsp/tpList.jsp';</script>");
            }
        }
    }
}
