<%@ page import="models.Task" %>
<%@ page import="managers.TaskManager" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.TaskStatusEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<%
    String message = session.getAttribute("message") + " " + session.getAttribute("userName") + " " + session.getAttribute("userSurname");
    session.removeAttribute(message);
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
<table border="1">
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>status</td>
        <td>deadline</td>
        <td>user</td>
    </tr>
    <%for (Task task : list) {%>
    <tr>
        <td><%=task.getId()%>
        </td>
        <td><%=task.getTitle()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><select>
            <option><%=task.getStatus()%>
            </option>
            <option><%=TaskStatusEnum.TODO%>
            </option>
            <option><%=TaskStatusEnum.ACTIVE%>
            </option>
            <option><%=TaskStatusEnum.FINISHED%>
            </option>
        </select>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td><%=task.getEmail()%>
        </td>
    </tr>
    <%}%>
</table>
<p>
    You can <a href="index.jsp">logout</a> from your account<br>
    <%session.invalidate();%>
</p>
</body>
</html>
