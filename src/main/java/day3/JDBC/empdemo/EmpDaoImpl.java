package day3.JDBC.empdemo;
import day3.JDBC.JDBCDemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl implements EmpDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    @Override
    public void addEmp(Emp emp) {
        try{
            conn = JDBCDemo.getConnection();
            String sql="insert into emp values (?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setString(3, emp.getJob());
            pstmt.setDouble(4, emp.getSal());
            pstmt.setInt(5, emp.getDeptno());

            int i= pstmt.executeUpdate();
            if(i>0){
                System.out.println("insert succ..");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(null,pstmt,conn);
        }
    }

    @Override
    public Emp findEmpByEmpno(int empno) {
        Emp emp=null;
        try{
            conn =JDBCDemo.getConnection();
            pstmt =conn.prepareStatement("select * from emp where empno=?");
            pstmt.setInt(1,empno);
            rs=pstmt.executeQuery();
            if(rs.next()){
                emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setSal(rs.getDouble("sal"));
                emp.setDeptno(rs.getInt("deptno"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(rs,pstmt,conn);
        }
        return emp;
    }

    @Override
    public int totalCount() {
        int count=0;
        try{
            conn=JDBCDemo.getConnection();
            pstmt=conn.prepareStatement("select count(*) from emp");
            rs=pstmt.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(rs,pstmt,conn);
        }
        return count;
    }

    @Override
    public void delEmpByEmpno(int empno) {
        try{
            conn=JDBCDemo.getConnection();
            pstmt=conn.prepareStatement("delete from emp where empno=?");
            pstmt.setInt(1,empno);
            int i=pstmt.executeUpdate();
            if(i>0){
                System.out.println("delete succ..");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(null,pstmt,conn);
        }
    }

    @Override
    public List<Emp> likeQueryEmps(String job) {
        List<Emp> emps=new ArrayList<>();
        Emp emp;
        try{
            conn=JDBCDemo.getConnection();
            pstmt=conn.prepareStatement("select * from emp where job like ?");
            pstmt.setString(1,"%"+job+"%");
            rs= pstmt.executeQuery();
            while(rs.next()){
                emp=new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setSal(rs.getDouble("sal"));
                emp.setDeptno(rs.getInt("deptno"));
                emps.add(emp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(rs,pstmt,conn);
        }
        return emps;
    }
}
