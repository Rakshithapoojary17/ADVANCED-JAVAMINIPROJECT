 <html>
<head>
<title>Delete Employee</title>

<style>
.box {
    width: 350px;
    margin: auto;
    margin-top: 80px;
    background: white;
    padding: 20px;
    box-shadow: 0px 0px 10px gray;
}
input {
    width: 100%;
    padding: 8px;
    margin: 10px 0;
}
input[type=submit] {
    background: red;
    color: white;
    border: none;
    cursor: pointer;
}
</style>

<script>
function confirmDelete() {
    return confirm("Are you sure you want to delete this employee?");
}
</script>

</head>

<body>

<div class="box">

<h2>Delete Employee</h2>

<!-- SUCCESS MESSAGE -->
<%
    if ("1".equals(request.getParameter("success"))) {
%>
    <p style="color:green;">Employee deleted successfully!</p>
<%
    }

    if (request.getAttribute("msg") != null) {
%>
    <p style="color:red;"><%=request.getAttribute("msg")%></p>
<%
    }
%>

<form action="DeleteEmployeeServlet" method="post"
      onsubmit="return confirmDelete()">

    Enter Emp No:
    <input type="number" name="empno" required min="1">

    <input type="submit" value="Delete Employee">
<a href="index.jsp">
    <button type="button"> Back</button>
</a>
</form>

</div>

</body>
</html>