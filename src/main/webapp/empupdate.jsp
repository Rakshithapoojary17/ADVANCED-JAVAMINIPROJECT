 <%@ page import="com.model.Employee" %>

<html>
<head>
<title>Update Employee</title>

<style>
.box {
    width: 400px;
    margin: auto;
    margin-top: 60px;
    background: white;
    padding: 20px;
    box-shadow: 0px 0px 10px gray;
}
input, select {
    width: 100%;
    padding: 8px;
    margin: 8px 0;
}
</style>
</head>

<body>

<div class="box">

<h2>Update Employee</h2>

<!-- SUCCESS / ERROR -->
<%
    if ("1".equals(request.getParameter("success"))) {
%>
    <p style="color:green;">Updated Successfully</p>
<%
    }

    if (request.getAttribute("msg") != null) {
%>
    <p style="color:red;"><%=request.getAttribute("msg")%></p>
<%
    }
%>

<!-- SEARCH FORM -->
<form action="UpdateEmployeeServlet" method="get">

    Enter Emp No:
    <input type="number" name="empno" required min="1">

    <input type="submit" value="Fetch">

</form>

<br>

<%
    Employee emp = (Employee) request.getAttribute("emp");

    if (emp != null) {

        // ✔ FIX DATE FORMAT
        String doj = emp.getDoj().toString();
%>

<!-- UPDATE FORM -->
<form action="UpdateEmployeeServlet" method="post">

    Emp No:
    <input type="text" name="empno" value="<%=emp.getEmpno()%>" readonly>

    Name:
    <input type="text" name="empname" value="<%=emp.getEmpname()%>" required>

    DoJ:
    <input type="date" name="doj" value="<%=doj%>" required>

    Gender:
    <select name="gender">
        <option value="Male" <%=emp.getGender().equals("Male")?"selected":""%>>Male</option>
        <option value="Female" <%=emp.getGender().equals("Female")?"selected":""%>>Female</option>
    </select>

    Salary:
    <input type="number" name="bsalary" value="<%=emp.getBsalary()%>" required>

    <input type="submit" value="Update Employee">

</form>

<%
    }
%>

<!-- ✔ BACK BUTTON (FIXED) -->
<br>
<button type="button" onclick="window.location.href='index.jsp'">
    Back
</button>

</div>

</body>
</html>