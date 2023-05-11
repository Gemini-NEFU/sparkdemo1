package day3.JDBC.empdemo;

import java.util.List;

public interface EmpDao {
    public void addEmp(Emp emp);
    public Emp findEmpByEmpno(int empno);

    public int totalCount();

    public void delEmpByEmpno(int empno);

    public List<Emp> likeQueryEmps(String job);
}
