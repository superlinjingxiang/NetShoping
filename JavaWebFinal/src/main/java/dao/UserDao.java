package dao;

import jdbcutil.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    /**
     * 用户登录验证
     * @param username	用户名
     * @param password	密码
     * @return
     */
    public static boolean login(String username, String password) {
        boolean flag = false;
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();

        String sql = "select * from user where username=? and password=?";
        rs = jdbc.executeQuery(sql, new Object[] { username, password });
        try {
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return flag;
    }


    /**
     * 用户注册
     * @param username	用户名
     * @param password	密码
     * @return
     */
    public static int register(String username, String password) {
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "insert into user(username,password) values(?,?)";
        int flag = jdbc.executeUpdate(sql, new Object[] { username, password });
        jdbc.close();
        return flag;
    }



    /**
     * 判断用户是否已注册
     * @param name	用户名
     * @return
     */
    public static boolean idRegister(String name) {
        boolean flag = false;
        ResultSet rs = null;
        JDBCUtil jdbc = new JDBCUtil();
        jdbc.getConnection();
        String sql = "select * from user where username=?";
        rs = jdbc.executeQuery(sql, new Object[] { name });
        try {
            if (rs.next()) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return flag;
    }

    public static int findUserId(String name){
        int id=-1;
        JDBCUtil jdbc=new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs=null;
        String sql="select id from user where username=?";
        rs=jdbc.executeQuery(sql,new Object[]{name});
        try {
            if(rs.next()){
                id=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return id;
    }


    public static boolean userIfVote(int titleid,int userid) {
        boolean flag=false;
        JDBCUtil jdbc=new JDBCUtil();
        jdbc.getConnection();
        ResultSet rs=null;
        String sql="select * from vote where articleid=? and voterid=?";
        rs=jdbc.executeQuery(sql, new Object[]{titleid,userid});
        try {
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.close();
        return flag;
    }
}
