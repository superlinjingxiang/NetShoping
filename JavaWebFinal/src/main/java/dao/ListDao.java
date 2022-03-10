package dao;

import domain.Listing;
import jdbcutil.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListDao {

    /**
     * 显示投票列表
     *
     * @return
     */
    public static List<Listing> viewList(String title, int start, int end) {
        List<Listing> viewlist = new ArrayList<>();
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null, rs2 = null;

        if (title != null) {
            String sql = "select title,COUNT(articleid) FROM article,optionss where article.id=articleid  and  title like '%"
                    + title + "%' GROUP BY articleid";
            rs = jdbc.executeQuery(sql, null);
        } else {
            String sql = "select  title,COUNT(articleid)  FROM article,optionss where article.id=articleid  GROUP BY articleid limit ?,?";
            rs = jdbc.executeQuery(sql, new Object[]{start, end});
        }

        String sql2 = "SELECT title,COUNT(DISTINCT  voterid) from article left join vote on article.id=vote.articleid  GROUP BY     articleid    ORDER BY  article.id  limit ?,?";
        rs2 = jdbc.executeQuery(sql2, new Object[]{start, end});

        try {
            while (rs.next()) {
                Listing ting = new Listing();
                ting.setTitle(rs.getString(1));
                ting.setOptionNum(rs.getInt(2));
                viewlist.add(ting);
            }
            while (rs2.next()) {
                System.out.println(rs2.getString(1));
                System.out.println(rs2.getInt(2));
                for (Listing listing : viewlist) {
                    if (listing.getTitle().equals(rs2.getString(1))) {
                        listing.setVoteNum(rs2.getInt(2));
                        break;
                    }
                }
            }

            for (Listing listing : viewlist) {
                System.out.println(listing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return viewlist;
    }

    /**
     * 添加投票标题
     *
     * @param title
     * @param type
     * @return
     */
    public static int addTitle(String title, int type) {
        int r = 0;
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "insert into article(title,type) value(?,?)";
        jdbc.executeUpdate(sql, new Object[]{title, type});
        sql = "select id from article where title=?";
        rs = jdbc.executeQuery(sql, new Object[]{title});
        try {
            if (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return r;
    }

    /**
     * 添加投票信息选项
     *
     * @param value
     * @param id
     */
    public static void addOption(String value, int id) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "insert into optionss(optionvalue,articleid) value(?,?)";
        jdbc.executeUpdate(sql, new Object[]{value, id});
        jdbc.close();
    }

    /**
     * 查找投票标题ID
     *
     * @param title
     * @return
     */
    public static int findTitleId(String title) {
        int r = 0;
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "select id from article where title=?";
        rs = jdbc.executeQuery(sql, new Object[]{title});
        try {
            if (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return r;
    }

    /**
     * 根据投票标题ID查找对应选项
     *
     * @param id
     * @return
     */
    public static List<String> findTitleOption(int id) {
        List<String> optionList = new ArrayList<>();
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "select optionvalue from optionss where articleid=?";
        rs = jdbc.executeQuery(sql, new Object[]{id});
        try {
            while (rs.next()) {
                optionList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return optionList;
    }

    /**
     * 查看该投票的类型，单选或多选
     *
     * @param id
     * @return
     */
    public static int findTitleType(int id) {
        List<String> optionList = new ArrayList<>();
        int type = -1;
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "select type from article where id=?";
        rs = jdbc.executeQuery(sql, new Object[]{id});
        try {
            if (rs.next()) {
                type = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return type;
    }

    /**
     * 添加投票
     *
     * @param titleid
     * @param optionid
     * @param userid
     */
    public static void addvote(int titleid, int optionid, int userid) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "insert into vote(articleid,optionid,voterid) value(?,?,?)";
        jdbc.executeUpdate(sql, new Object[]{titleid, optionid, userid});
        jdbc.close();

    }

    /**
     * 查找对应选项的ID
     *
     * @param option
     * @return
     */
    public static int findOptionId(String option) {
        int optionid = -1;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select id from optionss where optionvalue=?";
        rs = jdbc.executeQuery(sql, new Object[]{option});
        try {
            if (rs.next()) {
                optionid = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return optionid;

    }

    /**
     * 查看用户投票对应的选项ID
     *
     * @param titleid
     * @param userid
     * @return
     */
    public static List<Integer> findUserOption(int titleid, int userid) {
        List<Integer> inte = new ArrayList<>();
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select optionid  from vote where articleid=? and voterid=?";
        rs = jdbc.executeQuery(sql, new Object[]{titleid, userid});
        try {
            while (rs.next()) {
                inte.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return inte;

    }

    /**
     * 根据选项ID查找 对应选项
     *
     * @param optionid
     * @return
     */
    public static String findOption(int optionid) {
        String option = null;
        List<Integer> inte = new ArrayList<>();
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select optionvalue from optionss where id=?";
        rs = jdbc.executeQuery(sql, new Object[]{optionid});
        try {
            if (rs.next()) {
                option = rs.getString(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        jdbc.close();
        return option;

    }

    /**
     * 计算该投票 的总票数
     *
     * @param titleid
     * @return
     */
    public static int titleCount(int titleid) {
        int sum = 0;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select COUNT(articleid) from vote where articleid=?";
        rs = jdbc.executeQuery(sql, new Object[]{titleid});
        try {
            if (rs.next()) {
                sum = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return sum;
    }

    /**
     * 统计每个选项的票数
     *
     * @param title
     * @return
     */
    public static int optionNum(String title) {
        int sum = -1;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select count(optionid)  from  optionss,vote where optionid=optionss.id and optionvalue=?";
        rs = jdbc.executeQuery(sql, new Object[]{title});
        try {
            if (rs.next()) {
                sum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return sum;
    }

    /**
     * 删除该投票，用户 投的票
     *
     * @param id
     */
    public static void delVote(int id) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "delete from vote where articleid=?";
        jdbc.executeUpdate(sql, new Object[]{id});
        jdbc.close();
    }

    /**
     * 删除该投票对应的选项
     *
     * @param id
     */
    public static void delOption(int id) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "delete from optionss  where articleid=?";
        jdbc.executeUpdate(sql, new Object[]{id});
        jdbc.close();
    }

    /**
     * 删除投票
     *
     * @param id
     */
    public static void delArticle(int id) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "delete from article where id=?";
        jdbc.executeUpdate(sql, new Object[]{id});
        jdbc.close();
    }

    public static int artlcleCount() {
        int count = 0;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select count(id) from article";
        rs = jdbc.executeQuery(sql, null);
        try {
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        jdbc.close();
        return count;
    }

    public static boolean isReleaseVote(String title) {
        boolean flag = false;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs = null;
        String sql = "select id from article where title=" + title;
        rs = jdbc.executeQuery(sql, null);
        try {
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
