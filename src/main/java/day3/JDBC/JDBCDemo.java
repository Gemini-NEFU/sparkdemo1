package day3.JDBC;

import java.sql.*;

public class JDBCDemo {
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql:///spark?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
    private static String username="root";
    private static String password="3W9hyq9wl9com";

    static {
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void closeAll(ResultSet rs,Statement st,Connection conn) {
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(st!=null){
            try{
                st.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
