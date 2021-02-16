<%@ page import="managers.TaskManager" %>
<%@ page import="models.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Tasks</title>
</head>
<body>
<%
    List<Task> list = (List<Task>) request.getAttribute("allTasksList");
%>
<p>Here is the list of tasks by users</p>

<table border="1">
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>status</td>
        <td>deadline</td>
        <td>user</td>
    </tr>
    <% for (Task task : list) { %>
    <tr>
        <td><%=task.getId()%>
        </td>
        <td><%=task.getTitle() %>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td><%=task.getEmail()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>