
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Website</title>
  </head>
  <body>
  <%
    String message = "";
    if (session.getAttribute("message") != null){
      message = (String) session.getAttribute("message");
    }
    session.removeAttribute("message");
      %>

  <p style="color: red">
    <%= message %>
  </p>
  <form action="/login" method="post">
    <input type="text" name="email"><br>
    <input type="password" name="password"><br>
    <input type="submit" name="Ok"><br>
  </form>
  </body>
</html>
