package jdbcutil;

import java.sql.*;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/javawebporj";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 连接数据库。
    public Connection getConnection() {
        try {
            // 1.加载数据库驱动。
            Class.forName(DRIVER);

            // 2.建立数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 增删改 操作
     *
     * @param sql
     *            增删改的sql语句
     * @param obj
     *            传递过来的参数数组
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql, Object object[]) {
        int flag = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (object != null) {
                for (int i = 0; i < object.length; i++) {
                    ps.setObject(i + 1, object[i]);
                }
            }
            flag = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 查询操作
     *
     * @param sql
     *            查询的sql语句
     * @param obj
     *            传递的参数数组
     * @return
     */
    public ResultSet executeQuery(String sql, Object object[]) {
        try {
            ps = conn.prepareStatement(sql);
            if (object != null) {
                for (int i = 0; i < object.length; i++) {
                    ps.setObject(i + 1, object[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void close() {

        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
