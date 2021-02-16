<%@ page import="managers.TaskManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ManagerHome</title>
</head>
<body>
<%
    String message = session.getAttribute("message") + " " + session.getAttribute("userName") + " " + session.getAttribute("userSurname");
%>

<p style="color: royalblue">
    <%=message%>
    <%session.removeAttribute(message);%>
</p>
You can <a href="index.jsp">logout</a> from your account<br>
<% session.invalidate(); %>
<p>
    You Can choose one of the following options: <br>
</p>
<p>
    Register a new User<br>
</p>
<form action="/addUser" method="post">
    <input type="text" name="name"> : Name <br>
    <input type="text" name="surname"> : Surname <br>
    <input type="radio" id="manager" name="manager" value="Role">
    <label for="manager">Manager</label><br>
    <input type="radio" id="user" name="user" value="Role">
    <label for="user">User</label><br>
    <input type="text" name="email"> : Email <br>
    <input type="password" name="password"> : Password <br>
    <input type="submit" name="Ok"> <br>
</form>
<p>
    Add a new Task <br>
</p>
<form action="/addTask" method="get">
    <input type="text" name="title"> : Task name <br>
    <input type="text" name="description"> : Task description <br>
    <input type="text" name="status"> : Task status <br>
    <input type="date" name="deadline" placeholder="dd/mm/yyyy"> : Task deadline <br>
    <input type="text" name="userName" placeholder="user name"> <input type="text" name="userSurname"
                                                                       placeholder="user surname">
    <input type="text" name="userEmail" placeholder="user email">: Task user <br>
    <input type="submit" name="Ok"> <br>
</form>
<p>
    Delete user
</p>
<p style="color: blue">
    <% String msg = (String) request.getAttribute("usDel");
        if (msg != null) {%>
</p>
<p><%=msg%>
    <%request.removeAttribute("usDel");%>
</p>
<%}%>

<form action="/deleteUser" method="get">
    <input type="text" name="email"> : Input user's email to delete <br>
    <input type="submit" name="delete"> <br>
</form>
<p>
    To see all users list click <a href="/UserList"> here </a> <br>
</p>
<p>
    To see all tasks list click <a href="/allTasks"> here </a>
</p>
</body>
</html>
