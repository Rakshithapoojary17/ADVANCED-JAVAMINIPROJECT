 <%@ page import="java.sql.*" %>

<html>
<head>
<title>Report Result</title>

<style>
body {
    font-family: Arial;
    background-color: #f2f2f2;
}

.box {
    width: 80%;
    margin: auto;
    margin-top: 40px;
    background: white;
    padding: 20px;
    box-shadow: 0px 0px 10px gray;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid black;
    padding: 10px;
    text-align: center;
}

th {
    background-color: #4CAF50;
    color: white;
}
</style>
</head>

<body>

<div class="box">

<h2>Report Result</h2>

<%
    ResultSet rs = (ResultSet) request.getAttribute("result");

    if (rs != null && rs.isBeforeFirst()) {
%>

<table>

<tr>
    <th>Emp No</th>
    <th>Name</th>
    <th>DoJ</th>
    <th>Gender</th>
    <th>Salary</th>
</tr>

<%
        while (rs.next()) {
%>

<tr>
    <td><%=rs.getInt(1)%></td>
    <td><%=rs.getString(2)%></td>
    <td><%=rs.getDate(3)%></td>
    <td><%=rs.getString(4)%></td>
    <td><%=rs.getDouble(5)%></td>
</tr>

<%
        }
%>

</table>

<%
    } else {
%>

<p style="color:red;">No records found!</p>

<%
    }
%>
<a href="reports.jsp">
    <button type="button"> Back</button>
</a>
</div>

</body>
</html>