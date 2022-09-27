package JDBCTest;

import org.junit.Test;

import java.sql.*;

/**
 * @author by KingOfTetris
 * @date 2022/9/5
 */
public class JDBCtest {
    @Test
    public void test()  {

        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/test";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建连接
            conn = DriverManager.getConnection(url, username, password);
            //编写sql
            String sql = "select * from test where username = ?";
            //预编译sql
            psmt = conn.prepareStatement(sql);
            //设置参数
            psmt.setString(1,"lucy");
            //执行sql
            resultSet = psmt.executeQuery();
            //遍历结果集
            while (resultSet.next()){
                //得到结果
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                //注入对象
                User user = new User(username1,password1);

                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                psmt.close();
                conn.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
