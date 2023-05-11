package day3.JDBC;

import day3.JDBC.empdemo.Dept;
import day3.JDBC.empdemo.DeptDao;
import day3.JDBC.empdemo.DeptDaoImpl;
import day3.JDBC.empdemo.Emp;
import day3.JDBC.empdemo.EmpDao;
import day3.JDBC.empdemo.EmpDaoImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestMain {

    @Test
    public void testConnection(){
        try {
            Connection connection = JDBCDemo.getConnection();
            System.out.println("connection = " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryData(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = JDBCDemo.getConnection();
            String sql = "select * from emp";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getDouble(4)+","+rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCDemo.closeAll(rs, pstmt, connection);
        }
    }

    private EmpDao empDao=new EmpDaoImpl();

    @Test
    public void testAddEmp(){
        Emp emp = new Emp();
        emp.setEmpno(1004);
        emp.setEname("罗琦");
        emp.setJob("运维");
        emp.setSal(3400);
        emp.setDeptno(10);
        empDao.addEmp(emp);
    }

    @Test
    public void testFindEmp(){
        Emp emp = empDao.findEmpByEmpno(1004);
        System.out.println("emp = " + emp);
    }
    @Test
    public void testCount(){
        int i= empDao.totalCount();
        System.out.println("i="+i);
    }
    @Test
    public void testDelEmp(){
        empDao.delEmpByEmpno(1005);
    }
    @Test
    public void testLikeQueryEmps(){
        List<Emp> empList =empDao.likeQueryEmps("运维");
        for(Emp emp:empList){
            System.out.println("emp = "+emp);
        }
    }

    private DeptDao deptDao=new DeptDaoImpl();
    @Test
    public void testQueryEmpsDepts(){
        List<Dept> depts = deptDao.findDepts();
        for (Dept dept : depts) {
            System.out.println("部门信息:"+dept.getDname()+","+dept.getLoc());
            System.out.println("员工信息:");
            Emp empList = dept.getEmplist();
            System.out.println("empList.getEname() = " + empList.getEname());
        }
    }
}
