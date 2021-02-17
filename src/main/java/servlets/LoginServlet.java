package servlets;

import managers.UserManager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserManager userManager = new UserManager();
        List<User> allUsersList = userManager.getAllUsers();
        HttpSession session = req.getSession();

        for (User users : allUsersList) {
            if (users.getEmail().equals(req.getParameter("email"))
                    && users.getPassword().equals(req.getParameter("password"))
                    && users.getType().equals("manager")) {
                String message = "Welcome to your account";
                session.setAttribute("message", message);
                session.setAttribute("userName", users.getName());
                session.setAttribute("userSurname", users.getSurname());
                session.setAttribute("userEmail", users.getEmail());
                resp.sendRedirect("ManagerHome.jsp");
            }
        }

        for (User users : allUsersList) {
            if (users.getEmail().equals(req.getParameter("email"))
                    && users.getPassword().equals(req.getParameter("password"))
                    && users.getType().equals("user")) {
                String message = "Welcome to your account";
                session.setAttribute("message", message);
                session.setAttribute("userName", users.getName());
                session.setAttribute("userSurname", users.getSurname());
                session.setAttribute("userEmail", users.getEmail());
                resp.sendRedirect("UserHome.jsp");
            }
        }
    }
}