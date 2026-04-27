 package com.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.EmployeeDAO;
import com.model.Employee;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    // ================= GET =================
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            String empStr = req.getParameter("empno");

            if (empStr == null || empStr.trim().equals("")) {
                req.setAttribute("msg", "Please enter EmpNo");
                req.getRequestDispatcher("empupdate.jsp").forward(req, res);
                return;
            }

            int empno = Integer.parseInt(empStr);

            EmployeeDAO dao = new EmployeeDAO();
            Employee e = dao.getEmployee(empno);

            if (e != null) {
                req.setAttribute("emp", e);
            } else {
                req.setAttribute("msg", "Employee not found!");
            }

            req.getRequestDispatcher("empupdate.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Invalid EmpNo");
            req.getRequestDispatcher("empupdate.jsp").forward(req, res);
        }
    }

    // ================= POST =================
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            int empno = Integer.parseInt(req.getParameter("empno"));
            String name = req.getParameter("empname");
            String doj = req.getParameter("doj");
            String gender = req.getParameter("gender");
            double salary = Double.parseDouble(req.getParameter("bsalary"));

            // VALIDATION
            if (name == null || name.trim().length() < 2) {
                req.setAttribute("msg", "Name must be valid");
                req.getRequestDispatcher("empupdate.jsp").forward(req, res);
                return;
            }

            if (salary <= 0) {
                req.setAttribute("msg", "Salary must be greater than 0!");
                req.getRequestDispatcher("empupdate.jsp").forward(req, res);
                return;
            }

            Employee e = new Employee();
            e.setEmpno(empno);
            e.setEmpname(name);
            e.setDoj(Date.valueOf(doj));
            e.setGender(gender);
            e.setBsalary(salary);

            EmployeeDAO dao = new EmployeeDAO();
            int status = dao.updateEmployee(e);

            if (status > 0) {
                res.sendRedirect("empupdate.jsp?success=1");
            } else {
                req.setAttribute("msg", "Update failed!");
                req.getRequestDispatcher("empupdate.jsp").forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Error updating employee!");
            req.getRequestDispatcher("empupdate.jsp").forward(req, res);
        }
    }
}