<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<%List<User> list = (List<User>) request.getAttribute("usList");%>
<p>This is the list of all users</p> <br>

<table border="1">
    <tr>
        <td>id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
    </tr>
    <%for (User user : list) {%>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>