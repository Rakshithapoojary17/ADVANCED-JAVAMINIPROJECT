 <html>
<head>
<title>Display Employee</title>

<style>
.box {
    width: 500px;
    margin: auto;
    margin-top: 60px;
    background: white;
    padding: 20px;
    box-shadow: 0px 0px 10px gray;
}

table {
    width: 100%;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid black;
}

th, td {
    padding: 10px;
    text-align: center;
}

input {
    width: 100%;
    padding: 8px;
    margin: 10px 0;
}

input[type=submit] {
    background: blue;
    color: white;
    border: none;
    cursor: pointer;
}
</style>

</head>

<body>

<div class="box">

<h2>Display Employee</h2>

<!-- SEARCH FORM -->
<form action="DisplayEmployeeServlet" method="post">

    Enter Emp No:
    <input type="number" name="empno" required min="1">

    <input type="submit" value="Search">
</form>

<br>

<!-- ERROR MESSAGE -->
<%
    if (request.getAttribute("msg") != null) {
%>
    <p style="color:red;"><%=request.getAttribute("msg")%></p>
<%
    }
%>

<!-- DISPLAY RESULT -->
<%
    com.model.Employee emp = (com.model.Employee) request.getAttribute("emp");

    if (emp != null) {
%>

<table>
    <tr>
        <th>Emp No</th>
        <td><%=emp.getEmpno()%></td>
    </tr>

    <tr>
        <th>Name</th>
        <td><%=emp.getEmpname()%></td>
    </tr>

    <tr>
        <th>DoJ</th>
        <td><%=emp.getDoj()%></td>
    </tr>

    <tr>
        <th>Gender</th>
        <td><%=emp.getGender()%></td>
    </tr>

    <tr>
        <th>Salary</th>
        <td><%=emp.getBsalary()%></td>
    </tr>
</table>

<%
    }
%>

</div>

</body>
</html>