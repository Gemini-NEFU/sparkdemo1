package day3.JDBC.empdemo;

import day3.JDBC.JDBCDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    @Override
    public List<Dept> findDepts() {
        List<Dept> depts=new ArrayList<>();
        Dept dept;
        Emp emp;
        try {
            conn =JDBCDemo.getConnection();
            pstmt = conn.prepareStatement("select e.ename,d.dname,d.loc " +
                    "from emp e,dept d " +
                    "where e.deptno=d.deptno");
            rs=pstmt.executeQuery();
            while (rs.next()){
                dept=new Dept();
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));

                emp=new Emp();
                emp.setEname(rs.getString("ename"));
                dept.setEmplist(emp);
                depts.add(dept);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(rs,pstmt,conn);
        }
        return depts;
    }
}
