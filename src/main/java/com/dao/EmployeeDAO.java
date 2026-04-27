  package com.dao;

import java.sql.*;
import com.model.Employee;

public class EmployeeDAO {

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/employee_db",
            "root",
            "rakshi"
        );
    }

    // ================= ADD EMPLOYEE =================
    public int addEmployee(Employee e) throws Exception {

        Connection con = getConnection();
        int empno = 100;

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(Empno) FROM Employee");

        if (rs.next()) {
            int max = rs.getInt(1);

            if (max < 101) {
                empno = 100;
            } else {
                empno = max;
            }
        }

        empno++;
        

        // ✔ IMPORTANT: set into object
        e.setEmpno(empno);

        String sql = "INSERT INTO Employee(Empno, EmpName, DoJ, Gender, Bsalary) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, empno);
        ps.setString(2, e.getEmpname());
        ps.setDate(3, e.getDoj());
        ps.setString(4, e.getGender());
        ps.setDouble(5, e.getBsalary());

        int status = ps.executeUpdate();

        System.out.println("INSERT STATUS = " + status);

        return status;
    }
    // ================= UPDATE =================
    public int updateEmployee(Employee e) throws Exception {

        Connection con = getConnection();

        String sql = "UPDATE Employee SET EmpName=?, DoJ=?, Gender=?, Bsalary=? WHERE Empno=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, e.getEmpname());
        ps.setDate(2, e.getDoj());
        ps.setString(3, e.getGender());
        ps.setDouble(4, e.getBsalary());
        ps.setInt(5, e.getEmpno());

        return ps.executeUpdate();
    }

    // ================= DELETE =================
    public int deleteEmployee(int empno) throws Exception {

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM Employee WHERE Empno=?"
        );

        ps.setInt(1, empno);

        return ps.executeUpdate();
    }

    // ================= DISPLAY =================
    public Employee getEmployee(int empno) throws Exception {

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM Employee WHERE Empno=?"
        );

        ps.setInt(1, empno);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Employee e = new Employee();

            e.setEmpno(rs.getInt(1));
            e.setEmpname(rs.getString(2));
            e.setDoj(rs.getDate(3));
            e.setGender(rs.getString(4));
            e.setBsalary(rs.getDouble(5));

            return e;
        }

        return null;
    }


    // ===================================================
    // ================= REPORT 1 =========================
    // Employees whose names start with a letter
    // ===================================================
    public ResultSet getEmployeesByNameLetter(String letter) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM Employee WHERE EmpName LIKE ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, letter + "%");

        return ps.executeQuery();
    }

    // ===================================================
    // ================= REPORT 2 =========================
    // Employees with N or more years of service
    // ===================================================
    public ResultSet getEmployeesByServiceYears(int years) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM Employee WHERE TIMESTAMPDIFF(YEAR, DoJ, CURDATE()) >= ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, years);

        return ps.executeQuery();
    }

    // ===================================================
    // ================= REPORT 3 =========================
    // Employees earning more than specified salary
    // ===================================================
    public ResultSet getEmployeesBySalary(double salary) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM Employee WHERE Bsalary > ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, salary);

        return ps.executeQuery();
    }
}