package day3.JDBC.empdemo;

public class Dept {
    private int deptno;
    private String dname;
    private String loc;

    //添加员工集合属性
    private Emp emplist;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Emp getEmplist() {
        return emplist;
    }

    public void setEmplist(Emp emplist) {
        this.emplist = emplist;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", emplist=" + emplist +
                '}';
    }
}
