 package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.EmployeeDAO;
import com.model.Employee;

@WebServlet("/DisplayEmployeeServlet")
public class DisplayEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            int empno = Integer.parseInt(req.getParameter("empno"));

            EmployeeDAO dao = new EmployeeDAO();
            Employee e = dao.getEmployee(empno);

            if (e != null) {
                req.setAttribute("emp", e);
            } else {
                req.setAttribute("msg", "Employee not found!");
            }

            req.getRequestDispatcher("empdisplay.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Error retrieving employee!");
            req.getRequestDispatcher("empdisplay.jsp").forward(req, res);
        }
    }
}