 package com.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.EmployeeDAO;
import com.model.Employee;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            String empname = req.getParameter("empname");
            String doj = req.getParameter("doj");
            String gender = req.getParameter("gender");
            double salary = Double.parseDouble(req.getParameter("bsalary"));

            // validation
            if (empname == null || empname.trim().length() < 3) {
                req.setAttribute("msg", "Name must be 3+ letters");
                req.getRequestDispatcher("empadd.jsp").forward(req, res);
                return;
            }

            if (salary <= 0) {
                req.setAttribute("msg", "Salary must be > 0");
                req.getRequestDispatcher("empadd.jsp").forward(req, res);
                return;
            }

            Employee e = new Employee();
            e.setEmpname(empname);
            e.setDoj(Date.valueOf(doj));
            e.setGender(gender);
            e.setBsalary(salary);

            EmployeeDAO dao = new EmployeeDAO();
            int status = dao.addEmployee(e);

            if (status > 0) {

                req.setAttribute("msg",
                        "Employee Added Successfully. Your EmpNo = " + e.getEmpno());

                req.getRequestDispatcher("empadd.jsp").forward(req, res);

            } else {
                req.setAttribute("msg", "Insert failed!");
                req.getRequestDispatcher("empadd.jsp").forward(req, res);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("msg", "Error: " + ex.getMessage());
            req.getRequestDispatcher("empadd.jsp").forward(req, res);
        }
    }
}