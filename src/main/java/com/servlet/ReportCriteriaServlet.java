 
 package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;   // ✅ ADD
import java.io.*;

@WebServlet("/ReportCriteriaServlet")   // ✅ ADD
public class ReportCriteriaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("report_form.jsp");
        rd.forward(req, res);

    }
}