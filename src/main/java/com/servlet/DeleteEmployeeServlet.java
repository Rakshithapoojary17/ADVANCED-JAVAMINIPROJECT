 package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.EmployeeDAO;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            int empno = Integer.parseInt(req.getParameter("empno"));

            EmployeeDAO dao = new EmployeeDAO();
            int status = dao.deleteEmployee(empno);

            if (status > 0) {
                res.sendRedirect("empdelete.jsp?success=1");
            } else {
                req.setAttribute("msg", "Employee not found!");
                req.getRequestDispatcher("empdelete.jsp").forward(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Error while deleting employee!");
            req.getRequestDispatcher("empdelete.jsp").forward(req, res);
        }
    }
}