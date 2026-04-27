 <%
    String type = request.getParameter("type");
    if (type == null) type = "";
%>

<html>
<head>
<title>Report Input</title>
</head>

<body>

<h2>Report Input</h2>

<a href="report_form.jsp?type=name">Name start with letter</a> <br><br>
<a href="report_form.jsp?type=service">Experience</a> <br><br>
<a href="report_form.jsp?type=salary">By than more Salary</a><br><br>

<br><br>

<form action="ReportServlet" method="post">

<input type="hidden" name="type" value="<%=type%>">

<%
    if (type.equals("name")) {
%>
    Enter Starting Letter:
    <input type="text" name="letter" required>

<%
    } else if (type.equals("service")) {
%>
    Enter Years of Service:
    <input type="number" name="years" required>

<%
    } else if (type.equals("salary")) {
%>
    Enter Minimum Salary:
    <input type="number" name="salary" required>

<%
    } else {
%>

    <p style="color:red;">
        Please select a report from Reports page
    </p>

<%
    }
%>

<br>
<input type="submit" value="Generate Report">
<a href="reports.jsp">
    <button type="button"> Back</button>
</a>
</form>

</body>
</html>