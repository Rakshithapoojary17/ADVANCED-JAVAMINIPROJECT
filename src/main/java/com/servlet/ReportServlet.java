 package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.EmployeeDAO;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            String type = req.getParameter("type");

            EmployeeDAO dao = new EmployeeDAO();
            ResultSet rs = null;

            if (type == null) {
                throw new Exception("Type missing");
            }

            // ================= REPORT 1 =================
            if (type.equals("name")) {

                String letter = req.getParameter("letter");

                if (letter == null || letter.trim().equals("")) {
                    throw new Exception("Letter not provided");
                }

                rs = dao.getEmployeesByNameLetter(letter);
            }

            // ================= REPORT 2 =================
            else if (type.equals("service")) {

                String yearsStr = req.getParameter("years");

                if (yearsStr == null || yearsStr.trim().equals("")) {
                    throw new Exception("Years not provided");
                }

                int years = Integer.parseInt(yearsStr);

                rs = dao.getEmployeesByServiceYears(years);
            }

            // ================= REPORT 3 =================
            else if (type.equals("salary")) {

                String salStr = req.getParameter("salary");

                if (salStr == null || salStr.trim().equals("")) {
                    throw new Exception("Salary not provided");
                }

                double salary = Double.parseDouble(salStr);

                rs = dao.getEmployeesBySalary(salary);
            }

            else {
                throw new Exception("Invalid report type selected");
            }

            req.setAttribute("result", rs);

            // ✔ ALWAYS FORWARD TO RESULT PAGE
            req.getRequestDispatcher("report_result.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();

            req.setAttribute("msg", e.getMessage());

            req.getRequestDispatcher("report_result.jsp").forward(req, res);
        }
    }
}