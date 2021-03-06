package servlets;


import managers.UserManager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/UserList")
public class SeeAllUsersServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       List<User> usList = userManager.getAllUsers();
       req.setAttribute("usList", usList);
       req.getRequestDispatcher("UserList.jsp").forward(req, resp);
    }
}
