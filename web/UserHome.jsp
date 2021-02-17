<%@ page import="models.Task" %>
<%@ page import="managers.TaskManager" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.TaskStatusEnum" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<%
    String message = session.getAttribute("message") + " " + session.getAttribute("userName") + " " + session.getAttribute("userSurname");
    session.removeAttribute(message);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<p style="color: royalblue">
    <%=message%>
</p> <br>

<p>
    These are your active tasks
</p> <br>
<%
    String email = (String) session.getAttribute("userEmail");
    TaskManager taskManager = new TaskManager();
    List<Task> list = taskManager.getTaskByUser(email);%>
<form action="/userHome" method="post">
<table border="1">
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>status</td>
        <td>deadline</td>
        <td>user</td>
        <td>save</td>
    </tr>
    <%for (Task task : list) {%>
    <tr>
        <td><%=task.getId()%>
            <input type="hidden" name="id" value="<%=task.getId()%>">
        </td>
        <td><%=task.getTitle()%>
            <input type="hidden" name="title" value="<%=task.getTitle()%>">
        </td>
        <td><%=task.getDescription()%>
            <input type="hidden" name="desc" value="<%=task.getDescription()%>">
        </td>
        <td><select name="status">
            <option><%=task.getStatus()%>
            </option>
            <option name="statName" value="<%=TaskStatusEnum.TODO%>"><%=TaskStatusEnum.TODO%>
            </option>
            <option name="statName" value="<%=TaskStatusEnum.ACTIVE%>"><%=TaskStatusEnum.ACTIVE%>
            </option>
            <option name="statName" value="<%=TaskStatusEnum.FINISHED%>"><%=TaskStatusEnum.FINISHED%>
            </option>
        </select>
        </td>
        <td><%=sdf.format(task.getDeadline())%>
            <input type="hidden" name="deadline" value="<%=sdf.format(task.getDeadline())%>">
        </td>
        <td><%=task.getEmail()%>
            <input type="hidden" name="user" value="<%=task.getEmail()%>">
        </td>
        <td>
            <input type="submit">
        </td>
    </tr>
    <%}%>
</table>
</form>
<p>
    You can <a href="index.jsp">logout</a> from your account<br>
    <%session.invalidate();%>
</p>
</body>
</html>
