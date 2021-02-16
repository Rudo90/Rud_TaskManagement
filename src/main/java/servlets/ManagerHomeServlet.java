package servlets;

import managers.TaskManager;
import managers.UserManager;
import models.Task;
import models.TaskStatusEnum;
import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/addUser", "/addTask"})
public class ManagerHomeServlet extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private TaskManager taskManager = new TaskManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        StringBuilder role = new StringBuilder();
        if ("Role".equals(req.getParameter("manager"))) {
            role.append("manager");
        } else if ("Role".equals(req.getParameter("user"))) {
            role.append("user");
        }
        String type = role.toString();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserManager userManager = new UserManager();
        User user = User.builder()
                .name(name)
                .surname(surname)
                .type(type)
                .email(email)
                .password(password)
                .build();
        userManager.addUser(user);
        String msg = "User was added successfully!";
        req.setAttribute("msg", msg);
        req.getRequestDispatcher("ManagerHome.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String status = req.getParameter("status");
        try {
            Date date = sdf.parse(req.getParameter("deadline"));
            String userName = req.getParameter("userName");
            String userSurname = req.getParameter("userSurname");
            String userEmail= req.getParameter("userEmail");
            UserManager userManager = new UserManager();
            List<User> list = userManager.getAllUsers();
            for (User users : list) {
                if (users.getName().equals(userName) && users.getSurname().equals(userSurname)
                && users.getEmail().equals(userEmail)) {
                    Task task = Task.builder()
                            .title(title)
                            .description(desc)
                            .status(TaskStatusEnum.valueOf(status))
                            .deadline(date)
                            .email(userEmail)
                            .build();
                    taskManager.addTask(task);
                    break;
                }
            }
            String message = "Task was added successfully!";
            session.setAttribute("msg", message);
            resp.sendRedirect("ManagerHome.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
