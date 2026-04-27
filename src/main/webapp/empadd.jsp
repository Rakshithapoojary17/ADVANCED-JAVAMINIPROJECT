 <%@ page import="java.sql.*" %>

<html>
<head>
<title>Add Employee</title>

<script>
function validateForm() {

    let empno = document.forms["empForm"]["empno"].value;
    let name = document.forms["empForm"]["empname"].value;
    let salary = document.forms["empForm"]["bsalary"].value;

    let namePattern = /^[A-Za-z ]+$/;

    // ✔ EmpNo check (must be > 1)
    if (empno === "" || isNaN(empno) || Number(empno) <= 1) {
        alert("Enter valid EmpNo (greater than 1)");
        return false;
    }

    // ✔ Name check (min 2 characters)
    if (name === "" || name.trim().length <= 1) {
        alert("Name must be greater than 1 character");
        return false;
    }

    // ✔ Only letters allowed
    if (!namePattern.test(name)) {
        alert("Name should contain only letters (Example: Ramesh)");
        return false;
    }

    // ✔ Salary check
    if (salary === "" || isNaN(salary) || Number(salary) <= 0) {
        alert("Salary must be greater than 0");
        return false;
    }

    return true;
}
</script>

</head>

<body>

<h2>Add Employee</h2>

<%
    if ("1".equals(request.getParameter("success"))) {
%>
    <p style="color:green;">Employee Added Successfully</p>
<%
    }
if (request.getAttribute("msg") != null) {
%>
<p style="color:green;">
    <%=request.getAttribute("msg")%>
</p>
<%
}
%>

<form name="empForm" action="AddEmployeeServlet" method="post"
      onsubmit="return validateForm()">

<!-- ✔ MANUAL EMPNO (NO AUTO INCREMENT) -->
Emp No:
<input type="number" name="empno" required min="2">

<br><br>

Name:
<input type="text" name="empname" required>

<br><br>

DoJ:
<input type="date" name="doj" required>

<br><br>

Gender:
<select name="gender" required>
    <option value="Male">Male</option>
    <option value="Female">Female</option>
</select>

<br><br>

Salary:
<input type="number" name="bsalary" required min="1">

<br><br>

<input type="submit" value="Add Employee">

<!-- ✔ BACK BUTTON -->
<button type="button" onclick="history.back()">Back</button>

</form>

</body>
</html>